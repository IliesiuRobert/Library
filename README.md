Overview
This is a Library Management System implemented in Java, using Gradle for dependency management. The project follows an MVC (Model-View-Controller) architecture, integrating database operations and user interaction management.

Key Features & Modules
  Main Entry Point (Main.java)
    * Initializes the system.
    * Creates book objects and interacts with repository and service layers.
    * Demonstrates book creation, saving, retrieving, and deletion.

  Controllers (Manages UI Interaction)

    * AdminController.java: Handles administrative functions (adding/deleting books, user registration, and generating reports).
    * BookController.java: Manages book-related operations such as adding, deleting, and updating book details.
    * LoginController.java: Manages user authentication and login functionalities.

  Database Layer
    * DatabaseConnectionFactory.java & JDBConnectionWrapper.java: Establishes and manages database connections.
    * SQLTableCreationFactory.java: Handles SQL table creation.
    * Bootstrap.java: Initializes database schema and predefined user roles.

  Service Layer
    * BookService.java & BookServiceImpl.java: Business logic for book operations.
    * AuthenticationService.java: Manages user authentication and roles.

  UI Components
    * Uses JavaFX for the graphical user interface (GUI).
    * Admin & Book Views interact with controllers to manage library operations.
    * Displays alerts and confirmations based on user actions.

  Additional Functionalities
    * PDF Report Generation: Admin can generate a sales report in PDF format.
    * User Roles: Supports Admin, Employee and Customer roles with different permissions.
    * Caching System: Implements caching for book retrieval to optimize performance.

Technologies Used
    * Java (JDK 8+)
    * Gradle (for build automation)
    * JavaFX (for GUI)
    * JDBC (for database connectivity)
    * MySQL (as the database)
    * PDF Generation (for report creation)

This Library Management System is a structured Java application that enables book and user management, authentication, and report generation. With its modular MVC-based design, it provides scalability, maintainability, and efficiency in managing library operations.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Prezentare generală
Acesta aplicație este un Sistem de Gestionare a unei Biblioteci implementat în Java, utilizând Gradle pentru gestionarea dependențelor. Proiectul urmează arhitectura MVC (Model-View-Controller), integrând operațiuni de bază de date și gestionarea interacțiunii cu utilizatorii.

Caracteristici și Module
  Punctul principal de intrare (Main.java)
    * Inițializează sistemul.
    * Creează obiecte de tip carte și interacționează cu straturile repository și service.
    * Demonstrează crearea, salvarea, recuperarea și ștergerea cărților.

  Controlere (Gestionează interacțiunea UI)
    * AdminController.java – Gestionează funcțiile administrative (adăugarea/ștergerea cărților, înregistrarea utilizatorilor și generarea rapoartelor).
    * BookController.java – Administrează operațiunile legate de cărți, cum ar fi adăugarea, ștergerea și actualizarea detaliilor acestora.
    * LoginController.java – Gestionează autentificarea și funcționalitățile de conectare ale utilizatorilor.

  Baza de date
    * DatabaseConnectionFactory.java & JDBConnectionWrapper.java – Stabilesc și administrează conexiunile cu baza de date.
    * SQLTableCreationFactory.java – Gestionează crearea tabelelor SQL.
    * Bootstrap.java – Inițializează schema bazei de date și rolurile predefinite ale utilizatorilor.

  Service
    * BookService.java & BookServiceImpl.java – Conține logica de business pentru operațiunile cu cărți.
    * AuthenticationService.java – Gestionează autentificarea utilizatorilor și rolurile acestora.

  Componente UI
    * Utilizează JavaFX pentru interfața grafică.
    * Admin & Book Views interacționează cu controlerele pentru gestionarea operațiunilor bibliotecii.
    * Afișează alerte și mesaje de confirmare în funcție de acțiunile utilizatorului.

  Funcționalități suplimentare
    * Generare de rapoarte PDF – Admin-ul poate genera un raport de vânzări în format PDF.
    * Roluri utilizator – Suportă roluri Admin, Angajat și Client, fiecare având permisiuni diferite.
    * Sistem de caching – Implementare a unui mecanism de cache pentru optimizarea recuperării cărților.

  Tehnologii utilizate
    * Java (JDK 8+)
    * Gradle (pentru automatizarea construirii proiectului)
    * JavaFX (pentru interfața grafică)
    * JDBC (pentru conectivitate cu baza de date)
    * MySQL (ca bază de date)
    * Generare PDF (pentru crearea rapoartelor)

Acestă aplicație de Gestionare a unei Biblioteci este o aplicație Java bine structurată care permite administrarea cărților și utilizatorilor, autentificarea și generarea de rapoarte. Datorită designului modular bazat pe MVC, sistemul oferă scalabilitate, mentenabilitate și eficiență în gestionarea operațiunilor unei biblioteci.
