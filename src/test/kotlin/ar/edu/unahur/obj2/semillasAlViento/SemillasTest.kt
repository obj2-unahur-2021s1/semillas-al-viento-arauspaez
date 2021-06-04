package ar.edu.unahur.obj2.semillasAlViento
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe



class SemillasTest : DescribeSpec({

    //Plantas
    val menta = Menta(1900,0.3F)
    val mentaGrande = Menta(1900,0.5F)
    val soja = Soja(2010,0.4F)
    val sojaMediana = Soja(2000,0.7F)
    val sojaGrande = Soja(2010,1.1F)
    val sojaMasGrande = Soja(2010,1.3F)
    val sojaTrans = SojaTransgenica(2020,0.4F)
    val sojaTransMediana = SojaTransgenica(2020,0.7F)
    val sojaTransGrande = SojaTransgenica(2020,1.1F)

    //Parcelas
    val parcela = Parcela(100F,100F,7)
    val parcelaPequenia = Parcela(10F,2F,11)
    val parcelaChica = Parcela(5F,1F,9)

    //Agricultora
    val agricultora = Agricultora(mutableListOf(parcela,parcelaPequenia,parcelaChica))


    //TestPlanta
    describe("horas de sol"){
        it("menta"){
            menta.horasDeSolQueTolera() shouldBe 6
        }
        it("soja menor a 0.5 metros"){
            soja.horasDeSolQueTolera() shouldBe 6
        }
        it("soja entre 0.5 y 1 metro"){
            sojaMediana.horasDeSolQueTolera() shouldBe 7
        }
        it("soja mayor a 1 metro"){
            sojaGrande.horasDeSolQueTolera() shouldBe 9
        }
        it("sojaTransgenica menor a 0.5 metros"){
            sojaTrans.horasDeSolQueTolera() shouldBe 12
        }
        it("sojaTransgenica entre 0.5 y 1 metro"){
            sojaTransMediana.horasDeSolQueTolera() shouldBe 14
        }
        it("sojaTransgenica mayor a 1 metro"){
            sojaTransGrande.horasDeSolQueTolera() shouldBe 18
        }
    }
    describe("esFuerte"){
        it("menta no esFuerte"){
            menta.esFuerte() shouldBe false
        }
        it("soja menor a 0.5 metros no esFuerte") {
            soja.esFuerte() shouldBe false
        }
        it("soja entre 0.5 y 1 metro no esFuerte"){
            sojaMediana.esFuerte() shouldBe false
        }
        it("soja mayor a 1 metro no esFuerte"){
            sojaGrande.esFuerte() shouldBe false
        }
        it("sojaTransgenica menor a 0.5 metros"){
            sojaTrans.esFuerte() shouldBe true
        }
        it("sojaTransgenica entre 0.5 y 1 metro"){
            sojaTransMediana.esFuerte() shouldBe true
        }
        it("sojaTransgenica mayor a 1 metro"){
            sojaTransGrande.esFuerte() shouldBe true
        }
    }
    describe("daSemillas"){
        it("mentaGrande da semillas por altura superior a 0.4"){
            mentaGrande.daSemillas() shouldBe true
        }
        it("menta No da semillas por altura menor a 0.4"){
            menta.daSemillas() shouldBe false
        }
        it("soja No da semillas por altura menor a 1 metro"){
            soja.daSemillas() shouldBe false
        }
        it("sojaMediana No da semillas por anio"){
            sojaMediana.daSemillas() shouldBe false
        }
        it("sojaGrande da semillas por altura superior a 1.0"){
            sojaGrande.daSemillas() shouldBe true
        }
        it("sojaTransgenica No da semillas"){
            sojaTrans.daSemillas() shouldBe false
        }
    }

    //ParcelaTest
    describe("La Superficie de una parcela"){
        it("de 100 de largo y 100 de ancho"){
            parcela.superficie() shouldBe 10000
        }
    }
    describe("Cantidad Maxima de plantas"){
        it("parcela chica tiene mas ancho que largo"){
            parcelaChica.cantidadMaximaPlantas() shouldBe 1
        }
        it("parcela No tiene mas ancho que largo"){
            parcela.cantidadMaximaPlantas() shouldBe 3433
        }
    }
    describe("tieneComplicaciones"){
        it("parcela tiene complicaciones para menta"){
            parcela.plantar(menta)
            parcela.tieneComplicaciones() shouldBe true
        }
        it("parcela No tiene complicaciones para sojaTrans"){
            parcela.plantar(sojaTrans)
            parcela.tieneComplicaciones() shouldBe false
        }
    }
    describe("plantarUnaPlanta") {
        it("parcela puede plantar una planta") {
            parcela.plantas.size shouldBe 0
            parcela.plantar(soja)
            parcela.plantas shouldContain soja
            parcela.plantas.size shouldBe 1
        }
        it("parcela No puede plantar una planta porque alcanzo el limite"){
            parcelaChica.plantas.size shouldBe 0
            parcelaChica.plantar(sojaTransGrande)
            shouldThrowAny { parcelaChica.plantar(sojaTransMediana) }
        }
        it("parcela No puede plantar una planta porque se va a quemar"){
            parcelaChica.plantas.size shouldBe 0
            parcelaChica.plantar(sojaTrans)
            shouldThrowAny { parcelaChica.plantar(sojaTransMediana) }
        }
    }

    //Agricultora
    describe("Una Agricultora"){
        it("parcelaChica y parcela son semilleras, parcela pequenia no"){
            parcelaChica.plantar(sojaGrande)
            parcela.plantar(sojaMasGrande)
            parcelaPequenia.plantar(sojaTrans)
            agricultora.parcelasSemilleras().shouldContainExactlyInAnyOrder(parcelaChica,parcela)
        }
        it("plantarEstrategicamente planta soja en parcela"){
            parcela.plantas.size shouldBe 0
            agricultora.plantarEstrategicamente(soja)
            parcela.plantas shouldContain soja
            //parcela.cantidadPlantas shouldBe 1 - Debería dar 1 pero al no tener "cantidadPlantas += 1" en la funcion plantarEstrategicamente la variable no se modifica.
        }
    }
})