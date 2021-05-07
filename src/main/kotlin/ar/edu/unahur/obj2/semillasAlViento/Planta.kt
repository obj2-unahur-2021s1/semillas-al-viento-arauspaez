package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, var altura: Float) { //var? = En el enunciado menciona que la altura NUNCA cambiara.
  fun esFuerte() = this.horasDeSolQueTolera() > 10

  fun parcelaTieneComplicaciones(parcela: Parcela) =
  // :warning: Problema de SIMPLICIDAD = Agrega complejidad innecesaria pudiendo realizar el metodo en la clase 'Parcela' directamente.
    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }

  abstract fun horasDeSolQueTolera(): Int
  abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

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


  // :warning: Problema de SIMPLICIDAD
  // * La aclaracion de la rama 'False' es redundante.
  // * anioObtencionSemilla > 2007 && altura > 1 => Podria estar resuelto en un metodo a parte.
  override fun daSemillas(): Boolean  {
    if (this.esTransgenica) {
      return false
    }



    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}
