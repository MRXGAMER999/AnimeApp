package com.example.animeapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.animeapp.domain.repository.DataStoreOperations
import com.example.animeapp.util.Constants.PREFERENCES_KEY
import com.example.animeapp.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context): DataStoreOperations {
    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_KEY)
        val selectedCategoryKey = stringPreferencesKey(name = "selected_category")
        val selectedCategoriesKey = stringSetPreferencesKey(name = "selected_categories")
    }
    private val dataStore = context.dataStore
    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

    override suspend fun saveSelectedCategory(category: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.selectedCategoryKey] = category
        }
    }

    override fun readSelectedCategory(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val selectedCategory = preferences[PreferencesKey.selectedCategoryKey] ?: "Boruto"
                selectedCategory
            }
    }

    override suspend fun saveSelectedCategories(categories: Set<String>) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.selectedCategoriesKey] = categories
        }
    }

    override fun readSelectedCategories(): Flow<Set<String>> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val selectedCategories = preferences[PreferencesKey.selectedCategoriesKey] ?: setOf("Boruto")
                selectedCategories
            }
    }
}