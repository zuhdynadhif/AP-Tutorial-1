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

### Refleksi 2

1. After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? 

menurut saya unit test yang harus dibuat sebanyak mungkin hingga memenuhi semua case yang ada

2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.    

3. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! Please write your reflection inside the repository's README.md file.

code cleanliness harus diterapkan di seluruh bagian code project yang ada. Potensi terbesar kesalah menulis kode adalah pada pemberian variabel


### Module 2: Refleksi

#### 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
   
   Salah satu isu kode yang saya temukan adalah set Id dari product yang saya miliki. Ketika membuat unit test, saya bingung karena ketika menggunakan mock, saya tidak tahu apakah ID berhasil dibuat atau tidak. Lalu ssaya memperbaiki unit test sehingga bisa melakukan setId pada product yang digunakan.

#### 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

   Saya menggunakan GitHub Actions untuk melakukan CI/CD. Saya rasa implementasi yang saya lakukan sudah memenuhi definisi CI/CD. Karena setiap kali saya melakukan push ke branch master, maka akan dilakukan build dan test. Jika build dan test berhasil, maka akan dilakukan deploy ke server. Hal ini memenuhi definisi CI/CD yaitu melakukan build, test, dan deploy secara otomatis setiap kali ada perubahan pada kode.
