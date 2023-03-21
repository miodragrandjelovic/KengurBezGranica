using BackendKengur.Models;

namespace BackendKengur.Services.Interfaces
{
    public interface ISchoolService
    {

        School AddNewSchool(School school);
        List<School> GetAllSchools();
        School GetSchoolById(string id);
        void DeleteSchool(string id);
        List<School> SearchSchools(string school);

        School GetSchoolByNameAndCity(string school);

    }
}
