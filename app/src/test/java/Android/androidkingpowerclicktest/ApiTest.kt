package Android.androidkingpowerclicktest

import Android.androidkingpowerclicktest.models.Image
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApiTest {
    @Test
    fun `can get All image method` () {
        // call the api
        val api = API.create("https://jsonplaceholder.typicode.com/")
        val response = api.getAllImage()
        Assert.assertTrue(response != null)
    }
}