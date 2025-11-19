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

## 📦 Instalación y Ejecución Paso a Paso

### 📋 Prerrequisitos
Antes de comenzar, asegúrate de tener instalado:
- **Node.js 18+** y **npm** (para el frontend)
- **Java 17+** (para el backend)
- **MySQL/MariaDB** (base de datos)
- **Maven 3.6+** (para compilar el backend)
- **Git** (para clonar el repositorio)

### 🚀 Paso 1: Clonar el Repositorio
```bash
git clone https://github.com/tu-usuario/supermercado.git
cd supermercado
```

### 🗄️ Paso 2: Configurar la Base de Datos
```bash
# 1. Iniciar sesión en MySQL/MariaDB
mysql -u root -p

# 2. Crear la base de datos
CREATE DATABASE supermercado;

# 3. Seleccionar la base de datos
USE supermercado;

# 4. Importar el esquema y datos iniciales
SOURCE supermercado.sql;

# 5. Verificar que las tablas se crearon correctamente
SHOW TABLES;
```

**Nota:** El archivo `supermercado.sql` se encuentra en la raíz del proyecto.

### ⚙️ Paso 3: Configurar el Backend
```bash
# 1. Navegar al directorio del backend
cd backend

# 2. Revisar la configuración de la base de datos (opcional)
# El archivo de configuración está en: src/main/resources/application.properties
# Por defecto usa: localhost:3306/supermercado con usuario root

# 3. Compilar el proyecto con Maven
mvn clean install

# 4. Ejecutar el servidor Spring Boot
mvn spring-boot:run
```

**Alternativa:** Puedes importar el proyecto en NetBeans y ejecutarlo directamente desde el IDE.

**Verificación:** El backend estará disponible en `http://localhost:8080`
- Puedes probar con: `http://localhost:8080/api/categorias`

### 🎨 Paso 4: Configurar el Frontend
```bash
# 1. Abrir una nueva terminal (mantener el backend corriendo)

# 2. Navegar al directorio del frontend
cd frontend

# 3. Instalar las dependencias de Node.js
npm install

# 4. Iniciar el servidor de desarrollo de Angular
npm start
# o alternativamente:
ng serve
```

**Verificación:** El frontend estará disponible en `http://localhost:4200`

### 🔐 Paso 5: Iniciar Sesión en la Aplicación
1. Abre tu navegador en `http://localhost:4200`
2. Usa las credenciales de prueba:
   - **Email:** `mariana@gmail.com`
   - **Cédula:** `1234`
   - **Rol:** CAJERO

### 📱 Flujo de Trabajo Básico
1. **Crear Categoría:** Define los tipos de turnos (Cliente normal, Adulto Mayor, Embarazada)
2. **Crear Turno:** Asigna un turno a una categoría (estado: ESPERA)
3. **Llamar Turno:** Cambia el estado a ATENDIDO
4. **Finalizar Turno:** Registra el tiempo de espera (estado: FINALIZADO)

### 🔧 Troubleshooting (Solución de Problemas)

#### Problemas Comunes del Backend
```bash
# Error: "Access denied for user 'root'@'localhost'"
# Solución: Verifica las credenciales en application.properties

# Error: "Communications link failure"
# Solución: Asegúrate de que MySQL/MariaDB está corriendo

# Error: "Database 'supermercado' doesn't exist"
# Solución: Ejecuta el script supermercado.sql en MySQL
```

#### Problemas Comunes del Frontend
```bash
# Error: "npm ERR! code ENOENT"
# Solución: Asegúrate de estar en el directorio frontend/

# Error: "ng: command not found"
# Solución: Instala Angular CLI globalmente:
npm install -g @angular/cli

# Error: "Port 4200 is already in use"
# Solución: Usa otro puerto:
ng serve --port 4201
```

#### Problemas de Conexión
- **CORS:** Si hay errores de CORS, verifica que el backend esté corriendo en el puerto 8080
- **Base de datos:** Si el frontend no carga datos, verifica la conexión a MySQL y que el script SQL se ejecutó correctamente

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
