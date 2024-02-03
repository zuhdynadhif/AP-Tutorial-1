Zuhdy Nadhif Ayyasy
2206081212
Advanced Programming B

## Table of Content

1. [Refleksi 1](#refleksi-1)

## Refleksi 1

[Contents](#table-of-content)

### You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.

Prinsip clean code yang telah saya terapkan dalam proyek yang saya kembangkan adalah

1. **Meaningful Names**<br>
   Saya memberikan nama yang bermakna pada variabel, method, dan class yang saya buat. Hal ini bertujuan agar orang lain
   yang membaca kode saya dapat dengan mudah memahami apa yang saya maksud. Salah satu contohnya:
   ```java
    private String productId;
    private String productName;
    private int productQuantity;
   ```
   Pada kode di atas, saya memberikan nama yang bermakna pada atribut model Product.

2. **Functions**<br>
   Saya membuat fungsi yang pendek dan hanya melakukan satu hal. Hal ini bertujuan agar kode saya mudah dibaca dan
   dimengerti oleh orang lain.
   ```java
    public Product findById(String productId) {
        return productRepository.findById(productId);
    }
   ```
   Pada kode di atas, saya membuat fungsi yang hanya melakukan satu hal yaitu mencari product berdasarkan id.
3. **Comments**<br>
   Saya berusaha untuk tidak menulis comment, sebagai solusinya saya memberikan penamaan yang baik pada variabel,
   method, dan class yang saya buat.
4. **Objects and Data Structures**<br>
   Saya menggunakan object dan data struktur yang sesuai dengan kasus yang ada untuk menyimpan data dan mengakses data.
   Hal ini bertujuan agar kode saya mudah dimengerti oleh orang lain.
   ```java
    public class Product {
        private String productId;
        private String productName;
        private int productQuantity;
   }
   ```
   Pada kode di atas, saya menggunakan object untuk menyimpan data product.
   Saya juga menggunakan data structure yang sesuai untuk mengakses data.
   ```java
    @Repository
    public class ProductRepository {
        private List<Product> productData = new ArrayList<>();
    }
   ```

Pada kode di atas, saya menggunakan List untuk menyimpan data product.

### If you find any mistake in your source code, please explain how to improve your code.

Saya menemukan beberapa kekurangan dalam kode yang saya buat, yaitu:

1. Kesalahan logika
   Pada awal pengembangan proyek, saya membuat kesalahan logika berupa tidak adanya suatu atribut yang menjadi primary
   key. Sehingga ketika saya mengembangkan fitur _edit product_, tidak bisa mendapatkan product yang ingin diubah.
   Solusi yang saya lakukan adalah melakukan set atribut yang menjadi primary key.
    ```java
    public Product create(Product product) {
        product.setProductId(String.valueOf(UUID.randomUUID()));
        productData.add(product);
        return product;
    }
    ```
   Pada kode di atas, saya melakukan set atribut yang menjadi primary key yaitu productId. Sehingga ketika saya ingin
   mengubah product, saya bisa mencari product berdasarkan productId dengan menggunakan method `findById()`