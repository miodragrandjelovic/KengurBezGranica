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

        public School AddNewSchool(School school)
        {
           return schoolDAL.AddNewSchool(school);
        }

        public void DeleteSchool(string id)
        {
            schoolDAL.DeleteSchool(id);
        }

        public List<School> GetAllSchools()
        {
           return schoolDAL.GetAllSchools();
        }

        public School GetSchoolById(string id)
        {
            return schoolDAL.GetSchoolById(id);
        }
    }
}
