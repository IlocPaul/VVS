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
            driver.Url = "https://localhost:44318/";

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

            Task.Delay(1000).Wait();
            driver.FindElement(By.Id("registerLink")).Click();

            //ne folosim the input box si incercam sa ne inregistram (am facut sa dea fail pt a putea repeta testul de mai multe ori)
            driver.FindElement(By.Id("Email")).SendKeys("userNou@yahoo.com");
            Task.Delay(1000).Wait();
            driver.FindElement(By.Id("Password")).SendKeys("parola123");
            Task.Delay(1000).Wait();
            driver.FindElement(By.Id("ConfirmPassword")).SendKeys("parola123");
            Task.Delay(1000).Wait();

            //apasam pe buton pt a ne inregistra
            driver.FindElement(By.ClassName("btn-default")).Click();

            Task.Delay(2000).Wait();
            driver.FindElement(By.XPath("//a[@href='/' and text()='Home']")).Click();
            //ne intoarcem la pagina principala si validam un text

            var data = driver.FindElement(By.ClassName("lead")).Text;
            Console.WriteLine("Textul este: {0} ",data);
            //dam click pe un link
            driver.FindElement(By.XPath("//a[@href='https://go.microsoft.com/fwlink/?LinkId=301865' and text()='Learn more »']")).Click();
          
            
        }
    }
}
