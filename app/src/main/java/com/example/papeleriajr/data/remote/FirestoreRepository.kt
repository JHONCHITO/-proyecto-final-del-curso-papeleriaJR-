package com.example.papeleriajr.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.example.papeleriajr.data.model.Category
import com.example.papeleriajr.data.model.Product
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

/**
 * Repositorio para leer datos de Firestore.
 *
 * Colecciones esperadas:
 * - categories/{categoryId} -> { id, name, imageUrl }
 * - products/{productId}    -> { id, name, price, imageUrl, categoryId, tags[]? }
 */
class FirestoreRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    init {
        // Cache local habilitada (opcional)
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
    }

    // --------- Lecturas UNA VEZ (suspend) ---------

    suspend fun getCategories(): List<Category> = safeCall {
        db.collection("categories")
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(Category::class.java) }
            .sortedBy { it.name.lowercase() }
    }

    suspend fun getProducts(): List<Product> = safeCall {
        db.collection("products")
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(Product::class.java) }
    }

    suspend fun getProductsByCategory(categoryId: String): List<Product> = safeCall {
        db.collection("products")
            .whereEqualTo("categoryId", categoryId)
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(Product::class.java) }
    }

    /**
     * Búsqueda simple por nombre (demo).
     */
    suspend fun searchProductsLocally(query: String): List<Product> {
        if (query.isBlank()) return getProducts()
        val q = query.trim().lowercase()
        return getProducts().filter { it.name.lowercase().contains(q) }
    }

    // --------- Flows en TIEMPO REAL ---------

    fun categoriesFlow(): Flow<List<Category>> = callbackFlow {
        val reg = db.collection("categories")
            .addSnapshotListener { snap, err ->
                if (err != null) {
                    trySend(emptyList()).isSuccess
                    return@addSnapshotListener
                }
                val list = snap?.documents
                    ?.mapNotNull { it.toObject(Category::class.java) }
                    ?.sortedBy { it.name.lowercase() }
                    ?: emptyList()
                trySend(list).isSuccess
            }
        awaitClose { reg.remove() }
    }

    fun productsFlowByCategory(categoryId: String): Flow<List<Product>> = callbackFlow {
        val query = if (categoryId.isBlank()) {
            db.collection("products")
        } else {
            db.collection("products").whereEqualTo("categoryId", categoryId)
        }

        val reg = query.addSnapshotListener { snap, err ->
            if (err != null) {
                trySend(emptyList()).isSuccess
                return@addSnapshotListener
            }
            val list = snap?.documents?.mapNotNull { it.toObject(Product::class.java) } ?: emptyList()
            trySend(list).isSuccess
        }
        awaitClose { reg.remove() }
    }

    // --------- Utilidad ---------

    private suspend fun <T> safeCall(block: suspend () -> T): T {
        return try {
            block()
        } catch (e: Exception) {
            // Aquí puedes loguear con Crashlytics
            throw e
        }
    }
}
