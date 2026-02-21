# Configuración de Azure para moviecards

## Variables de Entorno Requeridas en Azure

Para que la aplicación funcione correctamente en Azure, es necesario configurar las siguientes variables de entorno en Azure App Service:

### Variables de Entorno Obligatorias

1. **SPRING_PROFILES_ACTIVE**
   - Valor: `prod`
   - Descripción: Activa el perfil de producción que usa la configuración correcta del servicio

2. **MOVIECARDS_SERVICE_URL**
   - Valor: `https://moviecards-service-henao-dub9awf7agfqeqa3.spaincentral-01.azurewebsites.net/api`
   - Descripción: URL base del servicio moviecards-service

### Configuración en Azure Portal

1. Ve a Azure Portal → App Services → `moviecards-henao`
2. En el menú lateral, selecciona **Configuration** → **Application settings**
3. Agrega las siguientes variables de entorno:

   ```
   SPRING_PROFILES_ACTIVE = prod
   MOVIECARDS_SERVICE_URL = https://moviecards-service-henao-dub9awf7agfqeqa3.spaincentral-01.azurewebsites.net/api
   ```

4. Haz clic en **Save** y reinicia la aplicación

### Alternativa: Usar Azure CLI

```bash
az webapp config appsettings set \
  --name moviecards-henao \
  --resource-group <nombre-del-resource-group> \
  --settings \
    SPRING_PROFILES_ACTIVE=prod \
    MOVIECARDS_SERVICE_URL=https://moviecards-service-henao-dub9awf7agfqeqa3.spaincentral-01.azurewebsites.net/api
```

### Verificación

Después de configurar las variables de entorno y reiniciar la aplicación:

1. Ve a los logs de la aplicación en Azure Portal
2. Busca mensajes que indiquen la URL del servicio que se está usando
3. Verifica que la URL sea la correcta

### Notas Importantes

- La aplicación debe reiniciarse después de cambiar las variables de entorno
- Si no se configura `SPRING_PROFILES_ACTIVE=prod`, la aplicación usará la configuración por defecto que apunta a `localhost:8080/api`
- Los logs de la aplicación mostrarán la URL del servicio que se está intentando usar
