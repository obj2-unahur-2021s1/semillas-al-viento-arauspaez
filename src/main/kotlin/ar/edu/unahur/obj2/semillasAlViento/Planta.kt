package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, var altura: Float) { //SIMPLICIDAD val altura- La altura NUNCA cambiara.
  fun esFuerte() = this.horasDeSolQueTolera() > 10

  fun parcelaTieneComplicaciones(parcela: Parcela) =
  // :warning: Problema de DES(ACOPLAMIENTO) en 'parcelaTieneComplicaciones()'
  // * Agrega complejidad innecesaria pudiendo realizar el metodo en la clase 'Parcela' directamente.
    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }

  abstract fun horasDeSolQueTolera(): Int
  abstract fun daSemillas(): Boolean //COHESIÓN- quedaría más claro crear una función condicionAlternativa() y delegar responsabilidades a esta función
                                    // para evaluar por un lado si esFuerte() y por otro la condicionAlternativa()
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

//SojaTransgenica sería mejor colocarla como una subclase de Soja para arrastrar los métodos (daSemillas() = False y horasDeSolQueTolera() = el doble que la Soja)y no meter toda la lógica en la misma Soja
//REDUNDANCIA MÍNIMA/COHESION
class Soja(anioObtencionSemilla: Int, altura: Float, val esTransgenica: Boolean) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera(): Int  {
    // ¡Magia de Kotlin! El `when` es como un `if` pero más poderoso:
    // evalúa cada línea en orden y devuelve lo que está después de la flecha.
    val horasBase = when {
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }

    return if (esTransgenica) horasBase * 2 else horasBase
  }



  override fun daSemillas(): Boolean  {
    // :warning: Problema de SIMPLICIDAD en 'daSemillas()'
    // * La aclaracion de la rama 'False' es redundante.
    // * anioObtencionSemilla > 2007 && altura > 1 => Podria estar resuelto en un metodo a parte.
    if (this.esTransgenica) {
      return false
    }



    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}
