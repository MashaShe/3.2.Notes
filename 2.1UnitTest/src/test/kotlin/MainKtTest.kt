import org.junit.Assert.assertEquals
import org.junit.Test


internal class MainKtTest {
    @Test
    fun comissionTestMastercard(){
        val cardType = "Mastercard"
        val monthSum = 7600000
        val transferSum = 10000000
        val expected = 62000
        val result = comissioncalc(cardType,monthSum,transferSum)
        assertEquals(result, expected)
    }
    @Test
    fun comissionTestMastercardSale(){
        val cardType = "Mastercard"
        val monthSum = 7400000
        val transferSum = 10000000
        val expected = 0
        val result = comissioncalc(cardType,monthSum,transferSum)
        assertEquals(result, expected)
    }

    @Test
    fun comissionTestMaestro(){
        val cardType = "Maestro"
        val monthSum = 7600000
        val transferSum = 10000000
        val expected = 62000
        val result = comissioncalc(cardType,monthSum,transferSum)
        assertEquals(result, expected)
    }
    @Test
    fun comissionTestMaestroSale(){
        val cardType = "Maestro"
        val monthSum = 7400000
        val transferSum = 10000000
        val expected = 0
        val result = comissioncalc(cardType,monthSum,transferSum)
        assertEquals(result, expected)
    }
    @Test
    fun comissionTestMastercardMaestroSpecialFunc(){
        val monthSum = 7600000
        val transferSum = 10000000
        val expected = 62000
        val result = masterComission(monthSum,transferSum)
        assertEquals(result, expected)
    }
    @Test
    fun comissionTestMastercardMaestroSpecialFuncSale(){
        val monthSum = 7400000
        val transferSum = 10000000
        val expected = 0
        val result = masterComission(monthSum,transferSum)
        assertEquals(result, expected)
    }

    @Test
    fun comissionTestVKPay(){
        val expected = 0
        val result = comissioncalc(transferSum = 1000000)
        assertEquals(result, expected)
    }

    @Test
    fun comissionTestVKPayDef(){
        val expected = commision(transferSum = 1000000)
        val result = commision(transferSum = 1000000)
        assertEquals(result, expected)
    }

    @Test
    fun comissionTestVisa(){
        val cardType = "Visa"
        val monthSum = 7600000
        val transferSum = 10000000
        val expected = 75000
        val result = comissioncalc(cardType,monthSum,transferSum)
        assertEquals(result, expected)
    }

    @Test
    fun comissionTestVisaMin(){
        val cardType = "Visa"
        val monthSum = 7600000
        val transferSum = 450000
        val expected = 3500
        val result = comissioncalc(cardType,monthSum,transferSum)
        assertEquals(result, expected)
    }

    @Test
    fun comissionTestMir(){
        val cardType = "Мир"
        val monthSum = 7600000
        val transferSum = 10000000
        val expected = 75000
        val result = comissioncalc(cardType,monthSum,transferSum)
        assertEquals(result, expected)
    }

    @Test
    fun comissionTestMirMin(){
        val cardType = "Мир"
        val monthSum = 7600000
        val transferSum = 450000
        val expected = 3500
        val result = comissioncalc(cardType,monthSum,transferSum)
        assertEquals(result, expected)
    }

    @Test
    fun comissionTestVisaMirSpecialFunc(){
        val monthSum = 7600000
        val transferSum = 10000000
        val expected = 75000
        val result = visaComission(monthSum,transferSum)
        assertEquals(result, expected)
    }

    @Test
    fun comissionTestDis(){
        val cardType = "Something"
        val monthSum = 7600000
        val transferSum = 450000
        val expected = 0
        val result = comissioncalc(cardType,monthSum,transferSum)
        assertEquals(result, expected)
    }

}