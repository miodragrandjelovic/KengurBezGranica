using BackendKengur.Models;

namespace BackendKengur.Services.Interfaces
{
    public interface ISchoolService
    {

        Task<School> AddNewSchool(School school);
        Task<List<School>> GetAllSchools();
        Task<School> GetSchoolById(string id);
        void DeleteSchool(string id);
        Task<List<School>> SearchSchools(string school);
        Task<School> GetSchoolByNameAndCity(string school);

    }
}
