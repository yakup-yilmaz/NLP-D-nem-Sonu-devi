# NLP Destekli Akıllı Haber Yönetim Sistemi

Bu proje, haber içeriklerini yönetmek ve Doğal Dil İşleme (NLP) teknikleri kullanarak otomatik özetler oluşturmak amacıyla geliştirilmiş tam kapsamlı bir Java Web uygulamasıdır. Kurumsal Java mimarisi (Jakarta EE) prensiplerine uygun olarak tasarlanmıştır.

## 🚀 Öne Çıkan Özellikler

*   **Akıllı Özetleme:** Haber içerikleri kaydedilirken sistem otomatik olarak özet oluşturur.
    *   **Yerel Özetleme:** Kelime frekansına dayalı özgün algoritma.
    *   **Yapay Zeka Entegrasyonu:** Python tabanlı dış servisler (Transformers/BERT vb.) ile HTTP üzerinden haberleşme.
*   **Yetkilendirme ve Güvenlik:** 
    *   Grup tabanlı yetki yönetimi (Admin, Editör, Okuyucu).
    *   `LoginFilter` ile izinsiz erişim engelleme.
*   **Haber ve Kategori Yönetimi:** Dinamik kategori yapısı ve tam CRUD (Ekle, Sil, Güncelle, Listele) işlemleri.
*   **Modern Web Arayüzü:** JSF (JavaServer Faces) kullanılarak geliştirilmiş, kullanıcı dostu panel yapısı.
*   **Sayfalama (Pagination):** Veritabanı performansını korumak için haber listelerinde sayfalama desteği.

## 🛠️ Teknoloji Yığını

*   **Backend:** Java (Jakarta EE / JSF)
*   **Frontend:** XHTML, CSS (PrimeFaces veya Standart JSF Components)
*   **Veritabanı:** SQL (PostgreSQL / MySQL)
*   **AI Entegrasyonu:** HTTP Client (Python FastAPI/Flask entegrasyonu için)
*   **Bağımlılık Yönetimi:** Maven/Ant

## 📂 Proje Yapısı

```text
src/java/
├── bean/       # JSF Managed Beans (Controller Katmanı)
├── dao/        # Veri Erişim Nesneleri (Veritabanı İşlemleri)
├── entity/     # Veri Modelleri (News, Category, User, Group)
├── util/       # Yardımcı Sınıflar (AI Service, DB Connect, Login Filter)
└── converter/  # JSF Veri Dönüştürücüleri

web/
├── panel/      # Yönetim paneli sayfaları
├── resources/  # CSS, JS ve Görsel dosyaları
└── index.xhtml # Karşılama ve Giriş sayfası
```

## ⚙️ Kurulum

1.  **Veritabanı:** SQL veritabanınızda `news`, `category`, `system_user` ve `system_group` tablolarını oluşturun.
2.  **Bağlantı Ayarları:** `util/DBConnect.java` dosyasındaki veritabanı kullanıcı adı ve şifre bilgilerini güncelleyin.
3.  **AI Servisi (Opsiyonel):** Gelişmiş özetleme özelliğini kullanmak için Python AI servisini `http://127.0.0.1:8000` adresinde başlatın.
4.  **Sunucu:** Projeyi Glassfish veya Payara gibi bir Java uygulama sunucusuna deploy edin.

## 🧠 Özetleme Algoritması Hakkında

Proje iki aşamalı bir özetleme stratejisi kullanır:
1.  **Frekans Analizi:** `SmartSummarizer.java` sınıfı, metindeki anlamlı kelimelerin sıklığını ölçerek en yüksek puanlı cümleleri seçer.
2.  **NLP Entegrasyonu:** `AiService.java` aracılığıyla, derin öğrenme modelleri kullanılarak üretilen özetler sisteme dahil edilir.

---
*Bu proje bir bitirme/dönem ödevi kapsamında, modern yazılım geliştirme prensipleri ve NLP uygulamalarını birleştirmek amacıyla geliştirilmiştir.*
