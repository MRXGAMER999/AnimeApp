package com.example.animeapp.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.CancellationException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object PaletteGenerator {
    suspend fun convertImageUrlToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {
        return try {
            Log.d("PaletteGenerator", "Loading image from: $imageUrl")
            val loader = ImageLoader(context = context)
            val request = ImageRequest.Builder(context = context)
                .data(imageUrl)
                .allowHardware(false)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build()
            val imageResult = loader.execute(request = request)
            if (imageResult is SuccessResult) {
                val bitmap = (imageResult.drawable as BitmapDrawable).bitmap
                Log.d("PaletteGenerator", "Successfully loaded bitmap: ${bitmap.width}x${bitmap.height}")
                bitmap
            } else {
                Log.w("PaletteGenerator", "Failed to load image: $imageResult")
                null
            }
        } catch (e: CancellationException) {
            Log.d("PaletteGenerator", "Image loading cancelled for: $imageUrl")
            throw e // Re-throw cancellation to propagate properly
        } catch (e: SocketTimeoutException) {
            Log.w("PaletteGenerator", "Timeout loading image: $imageUrl")
            null
        } catch (e: UnknownHostException) {
            Log.w("PaletteGenerator", "Network unavailable for image: $imageUrl")
            null
        } catch (e: Exception) {
            Log.e("PaletteGenerator", "Error loading image: $imageUrl", e)
            null
        }
    }

    fun extractColorsFromBitmap(bitmap: Bitmap): Map<String, String> {
        return try {
            Log.d("PaletteGenerator", "Extracting colors from bitmap: ${bitmap.width}x${bitmap.height}, config: ${bitmap.config}")
            
            // Ensure bitmap is software-based for Palette library compatibility
            val softwareBitmap = if (bitmap.config == Bitmap.Config.HARDWARE) {
                Log.d("PaletteGenerator", "Converting hardware bitmap to software bitmap")
                bitmap.copy(Bitmap.Config.ARGB_8888, false) ?: run {
                    Log.e("PaletteGenerator", "Failed to copy hardware bitmap to software bitmap")
                    throw RuntimeException("Unable to convert hardware bitmap to software bitmap")
                }
            } else {
                bitmap
            }
            
            val palette = Palette.from(softwareBitmap).generate()
            
            Log.d("PaletteGenerator", "Available swatches: " +
                "vibrant=${palette.vibrantSwatch != null}, " +
                "lightVibrant=${palette.lightVibrantSwatch != null}, " +
                "darkVibrant=${palette.darkVibrantSwatch != null}, " +
                "muted=${palette.mutedSwatch != null}, " +
                "lightMuted=${palette.lightMutedSwatch != null}, " +
                "darkMuted=${palette.darkMutedSwatch != null}, " +
                "dominant=${palette.dominantSwatch != null}")
            
            // Try to get vibrant color with fallbacks
            val vibrantColor = palette.vibrantSwatch 
                ?: palette.lightVibrantSwatch 
                ?: palette.mutedSwatch 
                ?: palette.dominantSwatch
            
            // Try to get dark vibrant color with fallbacks
            val darkVibrantColor = palette.darkVibrantSwatch 
                ?: palette.darkMutedSwatch
                ?: palette.mutedSwatch 
                ?: palette.dominantSwatch
                ?: vibrantColor
            
            val colors = mapOf(
                "vibrant" to parseColorSwatch(vibrantColor, "#000000"), // Black fallback
                "darkVibrant" to parseColorSwatch(darkVibrantColor, "#000000"), // Black fallback
                "onDarkVibrant" to parseBodyColor(darkVibrantColor?.bodyTextColor, "#FFFFFF")
            )
            
            Log.d("PaletteGenerator", "Generated colors: $colors")
            colors
        } catch (e: CancellationException) {
            Log.d("PaletteGenerator", "Color extraction cancelled")
            throw e // Re-throw cancellation
        } catch (e: Exception) {
            Log.e("PaletteGenerator", "Error extracting colors from bitmap", e)
            mapOf(
                "vibrant" to "#000000",
                "darkVibrant" to "#000000", 
                "onDarkVibrant" to "#FFFFFF"
            )
        }
    }

    private fun parseColorSwatch(color: Palette.Swatch?, fallback: String = "#000000"): String {
        return if (color != null) {
            val parsedColor = String.format("%06X", (0xFFFFFF and color.rgb))
            "#$parsedColor"
        } else {
            fallback
        }
    }

    private fun parseBodyColor(color: Int?, fallback: String = "#FFFFFF"): String {
        return if (color != null) {
            val parsedColor = String.format("%06X", (0xFFFFFF and color))
            "#$parsedColor"
        } else {
            fallback
        }
    }
}