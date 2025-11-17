# 🎫 Sistema de Gestión de Turnos - Supermercado

Sistema completo de gestión de turnos para supermercado con priorización automática, desarrollado con Angular y Spring Boot.

## 🚀 Tecnologías

### Frontend
- **Angular 20** - Framework principal
- **Tailwind CSS 3** - Estilos y diseño
- **TypeScript** - Lenguaje de programación
- **RxJS** - Manejo de observables

### Backend
- **Spring Boot 3.5.7** - Framework principal
- **Java 17** - Lenguaje de programación
- **Spring Data JPA** - ORM
- **Spring Security** - Seguridad (deshabilitada para desarrollo)
- **MySQL/MariaDB** - Base de datos

## ✨ Características

### 📋 Gestión de Turnos
- Crear turnos por categoría (Cliente normal, Adulto Mayor, Embarazada)
- Sistema de prioridad automático
- Llamar siguiente turno según prioridad
- Finalizar turnos con cálculo de tiempo de espera
- Registro de atenciones

### 👥 Roles de Usuario
- **Cajero**: Crear y gestionar turnos
- **Supervisor**: Eliminar turnos + funciones de cajero

### 🎨 Interfaz
- Diseño moderno y colorido
- Responsive (adaptable a móviles)
- Actualización automática cada 5 segundos
- Animaciones y transiciones suaves

## 📦 Instalación

### Prerrequisitos
- Node.js 18+ y npm
- Java 17+
- MySQL/MariaDB
- Maven (incluido en NetBeans)

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/supermercado.git
cd supermercado
```

### 2. Configurar la Base de Datos
```bash
# Crear la base de datos
mysql -u root -p
CREATE DATABASE supermercado;
USE supermercado;
SOURCE supermercado.sql;
```

### 3. Configurar el Backend
```bash
cd backend
# Editar application.properties si es necesario
# Compilar y ejecutar desde NetBeans o:
mvn spring-boot:run
```

El backend estará disponible en `http://localhost:8080`

### 4. Configurar el Frontend
```bash
cd frontend
npm install
npm start
```

El frontend estará disponible en `http://localhost:4200`

## 🗄️ Estructura del Proyecto

```
supermercado/
├── backend/                 # Spring Boot API
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/.../
│   │   │   │   ├── Controller/    # Endpoints REST
│   │   │   │   ├── Model/         # Entidades JPA
│   │   │   │   ├── Repository/    # Repositorios JPA
│   │   │   │   └── Service/       # Lógica de negocio
│   │   │   └── resources/
│   │   │       └── application.properties
│   └── pom.xml
├── frontend/               # Angular SPA
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/       # Componentes
│   │   │   ├── models/           # Interfaces TypeScript
│   │   │   └── services/         # Servicios HTTP
│   │   └── styles.css
│   ├── tailwind.config.js
│   └── package.json
└── supermercado.sql       # Dump de la base de datos
```

## 🔑 Credenciales de Prueba

**Usuario por defecto:**
- Email: `mariana@gmail.com`
- Cédula: `1234`
- Rol: CAJERO

## 📡 API Endpoints

### Turnos
- `POST /api/turnos/crear/{categoriaId}` - Crear turno
- `GET /api/turnos` - Listar turnos
- `PUT /api/turnos/llamar` - Llamar siguiente turno
- `PUT /api/turnos/finalizar/{id}/{empleadoId}` - Finalizar turno
- `DELETE /api/turnos/eliminar/{turnoId}/{empleadoId}` - Eliminar turno (solo supervisor)

### Empleados
- `POST /api/empleados/login` - Login
- `GET /api/empleados` - Listar empleados
- `POST /api/empleados` - Crear empleado

### Categorías
- `GET /api/categorias` - Listar categorías
- `POST /api/categorias` - Crear categoría

## 🎯 Algoritmo de Prioridad

El sistema implementa un algoritmo de prioridad simple:

1. Los turnos prioritarios (Adulto Mayor, Embarazada) se atienden antes que los normales
2. Dentro de cada categoría, se atienden por orden de llegada (FIFO)
3. Los estados de turno son: ESPERA → ATENDIDO → FINALIZADO

## 🛠️ Desarrollo

### Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm start
```

## 📝 Notas

- CORS está configurado para aceptar peticiones desde `http://localhost:4200`
- La seguridad está deshabilitada para facilitar el desarrollo
- El sistema calcula automáticamente los tiempos de espera
- Las atenciones quedan registradas en la base de datos
