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

        public School AddNewSchool(School school)
        {
           return schoolService.AddNewSchool(school);
        }

        public void DeleteSchool(string id)
        {
            schoolService.DeleteSchool(id);
        }

        public List<School> GetAllSchools()
        {
            return schoolService.GetAllSchools();
        }

        public School GetSchoolById(string id)
        {
            return schoolService.GetSchoolById(id);
        }

        public List<School> SearchSchools(string name)
        {
            return schoolService.SearchSchools(name);
        }
    }
}
