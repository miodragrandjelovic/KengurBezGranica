using BackendKengur.Models;

namespace BackendKengur.UI.Interfaces
{
    public interface ISchoolUI
    {
        Task<School> AddNewSchool(School school);
        Task<List<School>> GetAllSchools();
        Task<School> GetSchoolById(string id);
        void DeleteSchool(string id);
        Task<List<School>> SearchSchools(string name);

    }
}
