using BackendKengur.Models;
using BackendKengur.UI.Interfaces;
using BackendKengur.Services.Interfaces;

namespace BackendKengur.UI
{
    public class SchoolUI : ISchoolUI
    {
        private readonly ISchoolService schoolService;

        public SchoolUI(ISchoolService _schoolService)
        {
            schoolService = _schoolService;
        }

        public async Task<School> AddNewSchool(School school)
        {
           return await schoolService.AddNewSchool(school);
        }

        public void DeleteSchool(string id)
        {
            schoolService.DeleteSchool(id);
        }

        public async Task<List<School>> GetAllSchools()
        {
            return await schoolService.GetAllSchools();
        }

        public async Task<School> GetSchoolById(string id)
        {
            return await schoolService.GetSchoolById(id);
        }

        public async Task<List<School>> SearchSchools(string name)
        {
            return await schoolService.SearchSchools(name);
        }
    }
}
