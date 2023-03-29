namespace BackendKengur.Services.Interfaces
{
    public interface IFileService
    {
        void AddFile(string path,IFormFile file);
    }
}
