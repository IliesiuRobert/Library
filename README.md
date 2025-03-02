Overview  
&nbsp;This is a Library Management System implemented in Java, using Gradle for dependency management. The project follows an MVC (Model-View-Controller) architecture, integrating database operations and user interaction management.

Key Features & Modules:

  Main Entry Point (Main.java):          
  &ensp;* Initializes the system.  
  &ensp;* Creates book objects and interacts with repository and service layers.  
  &ensp;* Demonstrates book creation, saving, retrieving, and deletion.  

  Controllers (Manages UI Interaction):  
  &ensp;* AdminController.java: Handles administrative functions (adding/deleting books, user registration, and generating reports).  
  &ensp;* BookController.java: Manages book-related operations such as adding, deleting, and updating book details.  
  &ensp;* LoginController.java: Manages user authentication and login functionalities.  

  Database Layer:  
  &ensp;* DatabaseConnectionFactory.java & JDBConnectionWrapper.java: Establishes and manages database connections.  
  &ensp;* SQLTableCreationFactory.java: Handles SQL table creation.  
  &ensp;* Bootstrap.java: Initializes database schema and predefined user roles.  

  Service Layer:  
  &ensp;* BookService.java & BookServiceImpl.java: Business logic for book operations.  
  &ensp;* AuthenticationService.java: Manages user authentication and roles.  

  UI Components:  
  &ensp;* Uses JavaFX for the graphical user interface (GUI).  
  &ensp;* Admin & Book Views interact with controllers to manage library operations.  
  &ensp;* Displays alerts and confirmations based on user actions.  

  Additional Functionalities:      
  &ensp;* PDF Report Generation: Admin can generate a sales report in PDF format.  
  &ensp;* User Roles: Supports Admin, Employee and Customer roles with different permissions.  
  &ensp;* Caching System: Implements caching for book retrieval to optimize performance.  

Technologies Used:    
  &ensp;* Java (JDK 8+)  
  &ensp;* Gradle (for build automation)  
  &ensp;* JavaFX (for GUI)  
  &ensp;* JDBC (for database connectivity)  
  &ensp;* MySQL (as the database)  
  &ensp;* PDF Generation (for report creation)  

This Library Management System is a structured Java application that enables book and user management, authentication, and report generation. With its modular MVC-based design, it provides scalability, maintainability, and efficiency in managing library operations.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Prezentare generală  
&nbsp;Acesta aplicație este un Sistem de Gestionare a unei Biblioteci implementat în Java, utilizând Gradle pentru gestionarea dependențelor. Proiectul urmează arhitectura MVC (Model-View-Controller), integrând operațiuni de bază de date și gestionarea interacțiunii cu utilizatorii.

Caracteristici și Module:

  Punctul principal de intrare (Main.java):    
  &ensp;* Inițializează sistemul.  
  &ensp;* Creează obiecte de tip carte și interacționează cu straturile repository și service.  
  &ensp;* Demonstrează crearea, salvarea, recuperarea și ștergerea cărților.  

  Controlere (Gestionează interacțiunea UI):    
  &ensp;* AdminController.java – Gestionează funcțiile administrative (adăugarea/ștergerea cărților, înregistrarea utilizatorilor și generarea rapoartelor).  
  &ensp;* BookController.java – Administrează operațiunile legate de cărți, cum ar fi adăugarea, ștergerea și actualizarea detaliilor acestora.  
  &ensp;* LoginController.java – Gestionează autentificarea și funcționalitățile de conectare ale utilizatorilor.  

  Baza de date:  
  &ensp;* DatabaseConnectionFactory.java & JDBConnectionWrapper.java – Stabilesc și administrează conexiunile cu baza de date.  
  &ensp;* SQLTableCreationFactory.java – Gestionează crearea tabelelor SQL.  
  &ensp;* Bootstrap.java – Inițializează schema bazei de date și rolurile predefinite ale utilizatorilor.  

  Service:  
  &ensp;* BookService.java & BookServiceImpl.java – Conține logica de business pentru operațiunile cu cărți.  
  &ensp;* AuthenticationService.java – Gestionează autentificarea utilizatorilor și rolurile acestora.  

  Componente UI:  
  &ensp;* Utilizează JavaFX pentru interfața grafică.  
  &ensp;* Admin & Book Views interacționează cu controlerele pentru gestionarea operațiunilor bibliotecii.  
  &ensp;* Afișează alerte și mesaje de confirmare în funcție de acțiunile utilizatorului.  

  Funcționalități suplimentare:  
  &ensp;* Generare de rapoarte PDF – Admin-ul poate genera un raport de vânzări în format PDF.  
  &ensp;* Roluri utilizator – Suportă roluri Admin, Angajat și Client, fiecare având permisiuni diferite.  
  &ensp;* Sistem de caching – Implementare a unui mecanism de cache pentru optimizarea recuperării cărților.  

  Tehnologii utilizate:  
  &ensp;* Java (JDK 8+)  
  &ensp;* Gradle (pentru automatizarea construirii proiectului)  
  &ensp;* JavaFX (pentru interfața grafică)  
  &ensp;* JDBC (pentru conectivitate cu baza de date)  
  &ensp;* MySQL (ca bază de date)  
  &ensp;* Generare PDF (pentru crearea rapoartelor)  

Acestă aplicație de Gestionare a unei Biblioteci este o aplicație Java bine structurată care permite administrarea cărților și utilizatorilor, autentificarea și generarea de rapoarte. Datorită designului modular bazat pe MVC, sistemul oferă scalabilitate, mentenabilitate și eficiență în gestionarea operațiunilor unei biblioteci.
