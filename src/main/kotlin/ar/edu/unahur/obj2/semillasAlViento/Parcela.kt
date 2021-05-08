package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()
  var cantidadPlantas = 0
  // :warning: Problema de REDUNDANCIA MINIMA en 'cantidadPlantas'
  // * Se asocia al principio DRY.
  // * Podria resolverse con un 'plantas.size'.

  fun superficie() = ancho * largo
  fun cantidadMaximaPlantas() =
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo //REDUNDANCIA MINIMA ancho * largo ya está definido en superficie()
  // :warning: Problema de SIMPLICIDAD en 'cantidadMaximaPLantas()'
  // * Debe retornar directamente el valor que determina la funcion.

  //ROBUSTEZ - Falta el método tieneComplicaciones()

  fun plantar(planta: Planta) { //COHESION - El condicional del if y else if se puede unir con un && y delegar esa evaluacion en ótro método puedePlantar()
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      println("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      println("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
      cantidadPlantas += 1 // :warning: Problema de REDUNDANCIA en 'cantidadPlantas' (ver linea 6)
    }
  }
}

class Agricultora(val parcelas: MutableList<Parcela>) {
  // :warning: Problema de MUTACIONES CONTROLADAS en 'clase Agricultora'
  // * Podria estar creada en un archivo aparte para mayor claridad en el codigo.
  var ahorrosEnPesos = 20000

  // Suponemos que una parcela vale 5000 pesos
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }

  fun parcelasSemilleras() =
    // :warning: Problema de SIMPLICIDAD en 'parcelasSemilleras()'
    // * Hay 2 acciones diferentes sobre listas en una misma funcion.
    // * Apunta al principio KISS.
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
    laElegida.plantas.add(planta) //ROBUSTEZ - Existe un método en la Parcela para plantar() por lo que esta implementacion no es correcta
  }
}
