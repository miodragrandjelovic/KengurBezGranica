using BackendKengur.Models;

namespace BackendKengur.UI.Interfaces
{
    public interface ISchoolUI
    {
        School AddNewSchool(School school);
        List<School> GetAllSchools();
        School GetSchoolById(string id);
        void DeleteSchool(string id);
    }
}
