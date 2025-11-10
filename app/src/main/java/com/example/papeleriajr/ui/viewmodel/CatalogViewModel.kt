package com.example.papeleriajr.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papeleriajr.data.model.Category
import com.example.papeleriajr.data.model.Product
import com.example.papeleriajr.data.remote.FirestoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val repo: FirestoreRepository = FirestoreRepository()
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    fun loadCategories() {
        viewModelScope.launch {
            _categories.value = repo.getCategories()
        }
    }

    fun loadProductsByCategory(categoryId: String) {
        viewModelScope.launch {
            _products.value = repo.getProductsByCategory(categoryId)
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            _products.value = repo.searchProductsLocally(query)
        }
    }
}
