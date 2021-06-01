package ar.edu.unahur.obj2.semillasAlViento
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe


class SemillasTest : DescribeSpec({
    //Plantas
    val menta = Menta(1900,0.3F)
    val soja = Soja(2010,0.4F,false)
    val sojaTrans = Soja(2020,0.4F,true)

    describe("horas de sol"){
        it("menta"){
            menta.horasDeSolQueTolera() shouldBe 6
        }
        it("soja menor a 0.5 metros"){
            soja.horasDeSolQueTolera() shouldBe 6
        }
        it("soja entre 0.5 y 1 metro"){
            soja.altura = 0.7F
            soja.horasDeSolQueTolera() shouldBe 7
        }
        it("soja mayor a 1 metro"){
            soja.altura = 1.2F
            soja.horasDeSolQueTolera() shouldBe 9
        }
        it("sojaTransgenica menor a 0.5 metros"){
            sojaTrans.horasDeSolQueTolera() shouldBe 12
        }
        it("sojaTransgenica entre 0.5 y 1 metro"){
            sojaTrans.altura = 0.7F
            sojaTrans.horasDeSolQueTolera() shouldBe 14
        }
        it("sojaTransgenica mayor a 1 metro"){
            sojaTrans.altura = 1.2F
            sojaTrans.horasDeSolQueTolera() shouldBe 18
        }
    }
    describe("esFuerte"){ // si tolera más de 10 horas de sol al día
        it("menta no esFuerte"){
            menta.esFuerte() shouldBe false
        }
        it("soja menor a 0.5 metros no esFuerte") {
            soja.esFuerte() shouldBe false
        }
        it("soja entre 0.5 y 1 metro no esFuerte"){
            soja.altura = 0.7F
            soja.esFuerte() shouldBe false
        }
        it("soja mayor a 1 metro no esFuerte"){
            soja.altura = 1.2F
            soja.esFuerte() shouldBe false
        }
        it("sojaTransgenica menor a 0.5 metros"){
            sojaTrans.esFuerte() shouldBe true
        }
        it("sojaTransgenica entre 0.5 y 1 metro"){
            sojaTrans.altura = 0.7F
            sojaTrans.esFuerte() shouldBe true
        }
        it("sojaTransgenica mayor a 1 metro"){
            sojaTrans.altura = 1.2F
            sojaTrans.esFuerte() shouldBe true
        }
    }
    describe("daNuevasSemillas"){
        it("menta"){
            menta.daSemillas() shouldBe false
        }
        it("menta No da semillas"){
            menta.altura = 0.5F
            menta.daSemillas() shouldBe true
        }
        it("soja No da nuevas semillas por altura menor a 1 metro"){
            soja.daSemillas() shouldBe false
        }
        it("soja1 No da nuevas semillas por anio"){
            val soja1 = Soja(1900,1.5F,false)
            soja1.daSemillas() shouldBe false
        }
        it("soja"){
            soja.altura = 1.1F
            soja.daSemillas() shouldBe true
        }
        it("sojaTransgenica No da nuevas semillas"){
            sojaTrans.daSemillas() shouldBe false
        }
    }
})