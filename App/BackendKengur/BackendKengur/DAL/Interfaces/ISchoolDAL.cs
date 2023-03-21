using BackendKengur.Models;

namespace BackendKengur.DAL.Interfaces
{
    public interface ISchoolDAL
    {
        List<School> GetAllSchools();
        School GetSchoolById(string id);
        School AddNewSchool(School school);
        void DeleteSchool(string id);
        List<School> SearchSchools(string name);
        School GetSchoolByNameAndCity(string[] _school);
    }
}
