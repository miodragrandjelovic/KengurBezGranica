using BackendKengur.Models.Interfaces;
using System.Security.Cryptography;
using System.Text;

namespace BackendKengur.Models
{
    public class EncryptionManager: IEncryptionManager
    {
        public async Task<(byte[], byte[])> EncryptPassword(string password)
        {
            using (var hmac = new HMACSHA512())
            {
                using (var stream = new MemoryStream(System.Text.Encoding.UTF8.GetBytes(password)))
                {
                    var passwordKey = hmac.Key;

                    var passwordHash = await hmac.ComputeHashAsync(stream);

                    return (passwordHash, passwordKey);
                }      

            }
        }

        public async Task<bool> DecryptPassword(string userPassword, byte[] password, byte[] passwordKey)
        {
            bool answer = false;
            using (var hmac = new HMACSHA512(passwordKey))
            {
                using (var stream = new MemoryStream(System.Text.Encoding.UTF8.GetBytes(userPassword)))
                {
                    var passwordHash = await hmac.ComputeHashAsync(stream);

                    answer = passwordHash.SequenceEqual(password);
                }
            }

            return answer;
        }

    }
}
