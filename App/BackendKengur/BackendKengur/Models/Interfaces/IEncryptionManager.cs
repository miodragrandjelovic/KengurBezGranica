namespace BackendKengur.Models.Interfaces
{
    public interface IEncryptionManager
    {
        public Task<(byte[], byte[])> EncryptPassword(string password);
        public Task<bool> DecryptPassword(string password, byte[] passwordHash, byte[] passwordKey);
    }
}
