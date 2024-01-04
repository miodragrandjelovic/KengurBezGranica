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

        public async Task<School> AddNewSchool(School school)
        {
            await _schools.InsertOneAsync(school);
            return school;
        }
        public async Task<List<School>> GetAllSchools()
        {
            var schools = await _schools.FindAsync(school => true);
            return await schools.ToListAsync();
        }

        public async Task<School> GetSchoolById(string id)
        {
            var school = await _schools.FindAsync(school => school.Id == id);
            return await school.FirstOrDefaultAsync();
        }

        public void DeleteSchool(string id)
        {
            _ = _schools.DeleteOneAsync(school => school.Id == id);
        }

        public async Task<List<School>> SearchSchools(string name)
        {
            var schools = await _schools.FindAsync(schools => schools.Name.Contains(name));
            return await schools.ToListAsync();
        }

        public async Task<School> GetSchoolByNameAndCity(string[] _school)
        {
            var nameFilter = Builders<School>.Filter
            .Eq(s => s.Name, _school[0]);

            var cityFilter = Builders<School>.Filter
                .Eq(s => s.City, _school[1]);

            var filter = Builders<School>.Filter.And(nameFilter, cityFilter);
            var school = await _schools.FindAsync(filter);
            return await school.FirstOrDefaultAsync();
        }
    }
}
