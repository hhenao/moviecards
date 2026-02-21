# Script para crear el milestone Sprint 3 y los issues 3.1 y 3.2 en GitHub
# Requiere: GitHub CLI (gh) instalado y autenticado
# Ejecutar: .\create-milestone-issues.ps1

$repo = "hhenao/moviecards"  # Ajustar según tu repositorio

Write-Host "Creando milestone Sprint 3..."

# Crear milestone Sprint 3
gh api repos/$repo/milestones `
  --method POST `
  --field title="Sprint 3" `
  --field description="Integración con moviecards-service" `
  --field state="open" `
  --jq '.number' | Tee-Object -Variable milestoneNumber

Write-Host "Milestone creado con número: $milestoneNumber"

Write-Host "Creando issue 3.1..."

# Crear issue 3.1
gh api repos/$repo/issues `
  --method POST `
  --field title="3.1) Modificar el código de la aplicación en src/main" `
  --field body="Modificar el código de la aplicación en src/main para que utilice el servicio moviecards-service en lugar de los repositorios JPA locales.

Tareas:
- Agregar dependencia de RestTemplate o WebClient
- Crear cliente REST para comunicarse con moviecards-service
- Modificar MovieService, ActorService y CardService para usar el servicio externo
- Configurar URL del servicio en application.properties
- Eliminar dependencias de JPA si ya no son necesarias" `
  --field milestone=$milestoneNumber `
  --field labels="enhancement" | Out-Null

Write-Host "Issue 3.1 creado"

Write-Host "Creando issue 3.2..."

# Crear issue 3.2
gh api repos/$repo/issues `
  --method POST `
  --field title="3.2) Modificar el código de las pruebas en src/test" `
  --field body="Modificar el código de las pruebas en src/test para adaptarlas al uso del servicio moviecards-service.

Tareas:
- Actualizar pruebas unitarias para mockear las llamadas al servicio REST
- Actualizar pruebas de integración para usar un servicio mock o de prueba
- Actualizar pruebas end-to-end si es necesario
- Asegurar que todas las pruebas pasen con el nuevo servicio" `
  --field milestone=$milestoneNumber `
  --field labels="enhancement,testing" | Out-Null

Write-Host "Issue 3.2 creado"

Write-Host "¡Milestone e issues creados exitosamente!"
