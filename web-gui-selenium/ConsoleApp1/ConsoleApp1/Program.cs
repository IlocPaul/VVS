using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
           

            IWebDriver driver = new ChromeDriver();
            driver.Url = "https://localhost:44354/";

            //fullscreen + asteptam 10 secunde
            driver.Manage().Window.Maximize();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(5);

            //dam click pe About si se deschide pagina noua
            driver.FindElement(By.XPath("//a[@href='/Home/About' and text()='About']")).Click();

            DefaultWait<IWebDriver> fluentWait = new DefaultWait<IWebDriver>(driver);
            //asteptam 3 secunde pana revenim la pagina initiala
            Task.Delay(3000).Wait();

             driver.FindElement(By.XPath("//a[@href='/' and text()='Home']")).Click();
           // driver.FindElement(By.XPath("//a[@href='/Home/Contact' and text()='Contact']")).Click();
        }
    }
}
