package com.example.papeleriajr.data.model.local

import com.example.papeleriajr.data.model.Category
import com.example.papeleriajr.data.model.Product

object DemoData {
    // Cambia estas URLs por tus imágenes “exactas” si las tienes
    const val banner =
        "https://conceptodefinicion.de/wp-content/uploads/2018/03/Papeleria-2.jpg"

    private const val IMG_CUADERNO =
        "https://coversa.ec/wp-content/uploads/2023/05/Cuaderno-Estilo-800x800.png"
    private const val IMG_COLORES =
        "https://medios.papeleriamodelo.com/wp-content/uploads/2022/09/caja-de-colores-super-tirangulares-norma-mis-primeros-colores-x-12.jpg"
    private const val IMG_MARCADORES =
        "https://ejemplo.com.ar/wp-content/uploads/2023/08/marcadores-976x1024.jpg"
    private const val IMG_PLASTILINA =
        "https://www.libreriailusion.com/wp-content/uploads/2020/09/28-7501015211120-Plastilina-Pelikan.png"
    private const val IMG_BOLIGRAFO =
        "https://static1.diariosur.es/www/pre2017/multimedia/noticias/201506/04/media/cortadas/boligrafos-bic--575x323.jpg"
    private const val IMG_CINTA =
        "https://tse1.mm.bing.net/th/id/OIP.qcx6x6go0_rR65GP8JaheAHaHa?cb=ucfimg2ucfimg=1&rs=1&pid=ImgDetMain&o=7&rm=3"
    private const val IMG_PINTURA =
        "https://tse2.mm.bing.net/th/id/OIP.n3GupmcCHkIapFSZiaqyPwHaHa?cb=ucfimg2ucfimg=1&rs=1&pid=ImgDetMain&o=7&rm=3"
    private const val IMG_TIJERAS =
        "https://tse4.mm.bing.net/th/id/OIP.ZHBxS-2drrlENxo5-WK6PgHaDK?cb=ucfimg2ucfimg=1&rs=1&pid=ImgDetMain&o=7&rm=3"
    private const val IMG_CARPETA =
        "https://img.freepik.com/fotos-premium/carpeta-documentos-comerciales-carpetas-etiquetadas-codificadas-colores-creadas-ia-generativa_124507-170250.jpg"
    private const val IMG_BORRADOR =
        "https://tse2.mm.bing.net/th/id/OIP.iLR7jEQrSaQIU90LHi8E4wHaHa?cb=ucfimg2ucfimg=1&rs=1&pid=ImgDetMain&o=7&rm=3"
    private const val IMG_FOMI =
        "https://tse2.mm.bing.net/th/id/OIP.PO8NdAfIpPicqGPMrsKkDQHaGC?cb=ucfimg2ucfimg=1&rs=1&pid=ImgDetMain&o=7&rm=3"
    private const val IMG_RESALTADOR =
        "https://m.media-amazon.com/images/I/61rCy4PfnWL._AC_SL1500_.jpg"
    private const val IMG_BLOCK =
        "https://dcdn.mitiendanube.com/stores/004/288/177/products/whatsapp-image-2024-03-03-at-10-00-57-a32440c728073a03a817094768965806-1024-1024.jpeg"

    val categories = listOf(
        Category("cat-cuadernos", "cuadernos", IMG_CUADERNO),
        Category("cat-colores", "colores", IMG_COLORES),
        Category("cat-marcadores", "marcadores", IMG_MARCADORES),
        Category("cat-plastilina", "plastilina", IMG_PLASTILINA),
        Category("cat-boligrafos", "bolígrafos", IMG_BOLIGRAFO),
        Category("cat-cintas", "cintas", IMG_CINTA),
        Category("cat-pinturas", "pinturas", IMG_PINTURA),
        Category("cat-tijeras", "tijeras", IMG_TIJERAS),
        Category("cat-carpetas", "carpetas", IMG_CARPETA),
        Category("cat-borradores", "borradores", IMG_BORRADOR),
        Category("cat-fomi", "fomi", IMG_FOMI),
        Category("cat-resaltadores", "resaltadores", IMG_RESALTADOR),
        Category("cat-block", "block", IMG_BLOCK),
    )

    val products = listOf(
        Product("p1","Colores 12und JR", 9500.0, IMG_COLORES, "cat-colores"),
        Product("p2","Cuaderno Argollado", 7200.0, IMG_CUADERNO, "cat-cuadernos"),
        Product("p3","Bolígrafo Azul", 1800.0, IMG_BOLIGRAFO, "cat-boligrafos"),
        Product("p4","Plastilina Escolar", 6500.0, IMG_PLASTILINA, "cat-plastilina"),
        Product("p5","Marcadores Permanentes", 11000.0, IMG_MARCADORES, "cat-marcadores"),
        Product("p6","Acrílicos 6 Colores", 14500.0, IMG_PINTURA, "cat-pinturas"),
        Product("p7","Tijeras Escolares", 3500.0, IMG_TIJERAS, "cat-tijeras"),
        Product("p8","Carpeta A-Z", 8900.0, IMG_CARPETA, "cat-carpetas"),
        Product("p9","Borrador Blanco", 1200.0, IMG_BORRADOR, "cat-borradores"),
        Product("p10","Fomi x10 Colores", 9800.0, IMG_FOMI, "cat-fomi"),
        Product("p11","Resaltador Amarillo", 2600.0, IMG_RESALTADOR, "cat-resaltadores"),
        Product("p12","Block Rayado", 5200.0, IMG_BLOCK, "cat-block"),
        Product("p13","Cinta Transparente", 1700.0, IMG_CINTA, "cat-cintas"),
    )

    /** Retorna todos los productos para una categoría */
    fun allProductsFor(categoryId: String): List<Product> =
        products.filter { it.categoryId == categoryId }
}

