using BackendKengur.Models;

namespace BackendKengur.DAL.Interfaces
{
    public interface ISchoolDAL
    {
        Task<List<School>> GetAllSchools();
        Task<School> GetSchoolById(string id);
        Task<School> AddNewSchool(School school);
        void DeleteSchool(string id);
        Task<List<School>> SearchSchools(string name);
        Task<School> GetSchoolByNameAndCity(string[] _school);
    }
}
