using BackendKengur.Services.Interfaces;

namespace BackendKengur.Services
{
    public class FileService : IFileService
    {
        public void AddFile(string path, IFormFile file)
        {
            DirectoryInfo info = Directory.CreateDirectory(path); // kreiranje foldera do slika (images/razred/nivoZadatka)

            var sections = file.FileName.Split('.');
            string filePath;
            if (sections[1].Equals("avif")) // avif je novi format pa mora da se konvertuje zato sto nece da ga prikaze lepo
                filePath = Path.Combine(path,sections[0] + ".jpg");
            else
                filePath = Path.Combine(path,file.FileName);

            using (FileStream fs = new FileStream(filePath, FileMode.Create))
            {
                file.CopyTo(fs);
            }
        }
    }
}
