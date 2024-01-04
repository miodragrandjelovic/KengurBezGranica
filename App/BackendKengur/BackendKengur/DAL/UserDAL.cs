using BackendKengur.DAL.Interfaces;
using BackendKengur.Models;
using BackendKengur.Models.Interfaces;
using MongoDB.Driver;

namespace BackendKengur.DAL
{
    public class UserDAL : IUserDAL
    {

        private IMongoCollection<User> _user;

        public UserDAL(IKengurDatabaseSettings settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _user = database.GetCollection<User>(settings.UserCollectionName);
        }

        public async Task<bool> AddNewUser(User user)
        {
            if (user == null)
                return false;

            var result = await GetUser(user.Email);
            if (result != null)
                return false;

            await _user.InsertOneAsync(user);
            return true;
        }

        public async Task<bool> UpdateUser(string email,double points) 
        {
            var update = Builders<User>.Update
            .Inc("testNumber", 1)
            .Inc("sumPoints", points);

            var updateResult = await _user.UpdateOneAsync(u => u.Email == email, update);

            if (updateResult.ModifiedCount > 0)
                return true;
            return false;
        }

        public async Task<User> GetUser(string email)
        {
            var user = await _user.FindAsync(user => user.Email.Equals(email));
            return await user.FirstOrDefaultAsync();   
        }
    }
}
