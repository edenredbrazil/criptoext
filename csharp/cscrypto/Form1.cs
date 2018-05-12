using Org.BouncyCastle.Crypto;
using Org.BouncyCastle.Crypto.Encodings;
using Org.BouncyCastle.Crypto.Engines;
using Org.BouncyCastle.Crypto.Generators;
using Org.BouncyCastle.Crypto.Parameters;
using Org.BouncyCastle.Crypto.Prng;
using Org.BouncyCastle.Security;
using Org.BouncyCastle.Utilities.IO.Pem;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace cscryptoext
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }
        private void button2_Click(object sender, EventArgs e)
        {
            string publicKey = textBox2.Text;
            string @in = textBox3.Text;

            var bytesToDecrypt = Encoding.UTF8.GetBytes(@in);

            var encryptEngine = new Pkcs1Encoding(new RsaEngine());
            encryptEngine.Init(true, (AsymmetricKeyParameter)new Org.BouncyCastle.OpenSsl.PemReader(new StringReader(publicKey)).ReadObject());

            textBox4.Text = Convert.ToBase64String(encryptEngine.ProcessBlock(bytesToDecrypt, 0, bytesToDecrypt.Length)); 
        }
    }
}
