using BackendKengur.Models;
using BackendKengur.Services.Interfaces;
using BackendKengur.DAL.Interfaces;

namespace BackendKengur.Services
{
    public class SchoolService : ISchoolService
    {
        private readonly ISchoolDAL schoolDAL;

        public SchoolService(ISchoolDAL _schoolDAL)
        {
            schoolDAL = _schoolDAL;
        }

        public async Task<School> AddNewSchool(School school)
        {
           return await schoolDAL.AddNewSchool(school);
        }

        public void DeleteSchool(string id)
        {
            schoolDAL.DeleteSchool(id);
        }

        public async Task<List<School>> GetAllSchools()
        {
           return await schoolDAL.GetAllSchools();
        }

        public async Task<School> GetSchoolById(string id)
        {
            return await schoolDAL.GetSchoolById(id);
        }

        public async Task<List<School>> SearchSchools(string name)
        {
            return await schoolDAL.SearchSchools(name);
        }

        public async Task<School> GetSchoolByNameAndCity(string school)
        {
            string[] _school = school.Split(",");

            return await schoolDAL.GetSchoolByNameAndCity(_school);
        }

    }
}
