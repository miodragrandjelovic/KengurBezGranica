namespace BackendKengur.Services.Interfaces
{
    public interface IFileService
    {
        Task AddFile(string path,IFormFile file);
    }
}
