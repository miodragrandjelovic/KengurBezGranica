using BackendKengur.DAL.Interfaces;
using BackendKengur.Models;
using BackendKengur.Models.Interfaces;
using MongoDB.Driver;

namespace BackendKengur.DAL
{
    public class SchoolDAL : ISchoolDAL
    {
        private IMongoCollection<School> _schools;

        public SchoolDAL(IKengurDatabaseSettings settings, IMongoClient mongoClient)
        {
           var database = mongoClient.GetDatabase(settings.DatabaseName);
            _schools = database.GetCollection<School>(settings.SchoolCollectionName);
        }

        public School AddNewSchool(School school)
        {
            _schools.InsertOne(school);
            return school;
        }
        public List<School> GetAllSchools()
        {
            return _schools.Find(school => true).ToList();
        }

        public School GetSchoolById(string id)
        {
            return _schools.Find(school=> school.Id == id).FirstOrDefault();
        }

        public void DeleteSchool(string id)
        {
            _schools.DeleteOne(school => school.Id == id);
        }
        
    }
}
