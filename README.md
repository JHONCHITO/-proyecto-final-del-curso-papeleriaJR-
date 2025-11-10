$md = @'
# Papelería Jhon Chito – Catálogo y Pagos

**Autor:** Jhon Rique "Chito" Ruiz  
**Base de datos:** Firebase Firestore

---

## 1. Definición del problema

La papelería necesita un canal digital para **mostrar su catálogo** (colores, cuadernos, marcadores, etc.), permitir al cliente **ver detalles**, **agregar al carrito** y **dirigirse a pagos** sin fricción. Actualmente, la compra es presencial y manual, lo que genera:
- Pérdida de ventas por falta de catálogo online.
- Dificultad para consultar disponibilidad y precios.
- Proceso lento para cerrar una compra.

**Problema central:** No existe un flujo digital unificado desde catálogo → selección → **pago**, exigido por el curso y por la necesidad real del negocio.

---

## 2. Objetivos

### Objetivo general
Implementar una aplicación de catálogo para la papelería que permita **navegar productos por categorías** y que, al finalizar la selección (o al elegir un producto), **redirija a la pantalla de pagos**, cumpliendo con los requisitos del curso.

### Objetivos específicos
1. Conectar la app a Firestore con colecciones `categories`.
2. Mostrar productos por categoría y detalle de producto.
3. Permitir agregar productos al carrito y actualizar cantidades.
4. Implementar el flujo de **redirección a pago** desde el catálogo/detalle/carrito.
5. Registrar pedidos con datos básicos.
6. Mantener una UI clara y móvil-first para facilitar la compra.

---

## 3. Requerimientos Funcionales (actualizados según Firestore PapeleríaJR)

La aplicación “Papelería Jhon Chito” utiliza **Firebase Firestore** como base de datos en tiempo real, con dos colecciones principales:
- **categories**: contiene las categorías de productos (ej. `cat-block`, `cat-cuadernos`, `cat-colores`, etc.) con campos `id`, `name` e `imageUrl`.
- **productos**: contiene los productos asociados a cada categoría con campos `nombre`, `descripcion`, `precio`, `imagenUrl`, `disponible` y `categoriaId`.

### Requerimientos Funcionales

**RF1.** Listar las **categorías** desde Firestore (`categories`) mostrando su nombre e imagen (`name`, `imageUrl`).  
Ejemplo: `cat-block`, `cat-colores`, `cat-cuadernos`, etc.

**RF2.** Listar los **productos asociados a cada categoría** leyendo los documentos de Firestore (`productos`) donde `categoriaId` coincide con el ID de la categoría seleccionada.  
Ejemplo: al elegir *“cuadernos”*, se cargan los productos con `categoriaId = cat-cuadernos`.

**RF3.** Mostrar el **detalle de un producto** con información completa: `nombre`, `precio`, `descripcion`, `imagenUrl` y disponibilidad (`disponible`).

**RF4.** Permitir **agregar productos al carrito** y modificar las cantidades antes de pagar.

**RF5.** Calcular el **subtotal y total automáticamente** al agregar/eliminar productos del carrito.

**RF6.** **Redirigir a la pantalla de pagos** al presionar “Finalizar compra” o “Comprar ahora” en un producto.

**RF7.** Registrar la **intención de compra** en Firestore antes del pago (usuario, items, total, fecha).

**RF8.** Mostrar **estado disponible/no disponible** del producto (`disponible`).

**RF9.** Permitir **búsqueda y filtrado** por nombre/categoría.

**RF10.** Mostrar **confirmación post-pago** (éxito/cancelado).  
Flujo final: *Catálogo → Detalle → Carrito → Pagos → Confirmación*.

### Relación con la base de datos

| Colección | Campos principales | Ejemplo |
|---|---|---|
| **categories** | `id`, `name`, `imageUrl` | `{ id: "cat-block", name: "block", imageUrl: "https://dcdn.mitiendanube.com/..." }` |
                 | `nombre`, `descripcion`, `precio`, `imagenUrl`, `disponible`, `categoriaId` | `{ nombre: "Cuaderno Norma", precio: 6900, categoriaId: "cat-cuadernos" }` |

---

## 4. Requerimientos No Funcionales

- **RNF1.** Rendimiento: carga del catálogo < 2s en red estable.
- **RNF2.** Usabilidad: botones visibles “Agregar” y “Pagar”.
- **RNF3.** Seguridad: no exponer llaves privadas; pagos por HTTPS (Stripe u otro).
- **RNF4.** Disponibilidad: operación 24/7 salvo mantenimiento.
- **RNF5.** Compatibilidad: UI móvil-first (usable también en desktop/emulador).

---

## 5. Historias de Usuario (resumen)

HU1 Ver categorías· 

HU2 Ver productos por categoría·

HU3 Ver detalle· 

HU4 Agregar al carrito· 

HU5 Ajustar cantidades · HU6 Ver total· 

HU7 Pagar desde producto/carrito· 

HU8 Confirmación éxito/error· 

HU9 Admin CRUD productos· 

HU10 Búsqueda por nombre· 

HU11 Filtrar por disponibilidad·

HU12 Imágenes nítidas·

HU13 Persistencia del carrito· 

HU14 Registrar pedido previo al pago· 

HU15 Regresar desde pagos.

---

## 6. Pantallas del proyecto (único integrante)

**Integrante:** Jhon Rique "Chito" Ruiz

| # | Pantalla | Descripción |
|---|---|---|
| 1 | **Catálogo / Categorías** | Lista categorías desde `categories` y navega a productos. |
| 2 | **Lista y detalle** | Productos por categoría; botón “Comprar ahora” → pagos. |
| 3 | **Pago / Finalizar compra** | Resumen y confirmación; redirige a pantalla de éxito/cancelado. |

**Flujo:** Catálogo → Detalle → Pagos → Confirmación

---

## 7. Mockups y Transiciones

**Estado actual del flujo (sin pasarela real):**  
La aplicación navega desde el catálogo hasta la pantalla de **Pagos**. El botón **“Pagar ahora”** funciona y dirige a **Pagos**, pero **no existe integración con una plataforma** (Stripe/PayU/etc.). Por tanto, la demostración termina en **Pagos**.

**Archivos (en `/docs/mockups/`):**  
`01_home_categorias.png`, `02_lista_productos.png`, `03_detalle_producto.png`,  
`04_carrito.png`, `05_pagos.png`

### 🔁 Transiciones implementadas
- **Inicio / Categorías → Lista de productos:** al seleccionar una categoría.
- **Lista → Detalle:** al tocar un producto (ej. “Cuaderno Argollado”).
- **Detalle → Pagos:** al presionar **“Pagar ahora”**.

### 🧭 Diagrama del flujo (estado actual)

```mermaid
flowchart TD

    A[Inicio / Categorías] --> B[Lista de productos]

    B --> C[Detalle de producto]

    C --> E[Pagos (simulada)]



## 8. Evidencias y Entrega

### 🎥 Videos (clicables con miniatura)

**Video 1 – Flujo completo:**  
[▶️ Ver en YouTube](https://youtube.com/shorts/EIio8SajZkY?si=Ltyo2SsolEKjaKyo)  
[![Video 1](https://img.youtube.com/vi/EIio8SajZkY/hqdefault.jpg)](https://youtube.com/shorts/EIio8SajZkY?si=Ltyo2SsolEKjaKyo)

**Video 2 – Base de datos Firebase (categorías):**  
[▶️ Ver en YouTube](https://youtube.com/shorts/VmxhvixqIlE?si=0djqtyibw1Wom1cM)  
[![Video 2](https://img.youtube.com/vi/VmxhvixqIlE/hqdefault.jpg)](https://youtube.com/shorts/VmxhvixqIlE?si=0djqtyibw1Wom1cM)

---

### 🖼️ Imágenes del proyecto (Miro)

- [Mockup 1](https://miro.com/app/board/uXjVJslq8rE=/?moveToWidget=3458764647797484139&cot=14)
- [Mockup 2](https://miro.com/app/board/uXjVJslq8rE=/?moveToWidget=3458764647797484126&cot=14)
- [Mockup 3](https://miro.com/app/board/uXjVJslq8rE=/?moveToWidget=3458764647797484147&cot=14)
- [Mockup 4](https://miro.com/app/board/uXjVJslq8rE=/?moveToWidget=3458764647797484164&cot=14)
- [Mockup 5](https://miro.com/app/board/uXjVslq8rE=/?moveToWidget=3458764647797484180&cot=14)
- [Mockup 5](https://miro.com/app/board/uXjVJslq8rE=/?moveToWidget=3458764647809310349&cot=14)


---
