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

        public bool AddNewUser(User user)
        {
            if (GetUser(user.Email) != null)
                return false;
            if (user == null)
                return false;
            _user.InsertOne(user);
            return true;
        }

        public bool UpdateUser(string email,double points) 
        {
            var update = Builders<User>.Update
            .Inc("testNumber", 1)
            .Inc("sumPoints", points);

            var updateResult = _user.UpdateOne(u => u.Email == email, update);

            if (updateResult.ModifiedCount > 0)
                return true;
            return false;
        }

        public User? GetUser(string email)
        {
            return _user.Find(user=> user.Email.Equals(email)).FirstOrDefault();   
        }
    }
}
