using BackendKengur.DAL.Interfaces;
using BackendKengur.Models;
using BackendKengur.Models.Interfaces;
using MongoDB.Driver;

namespace BackendKengur.DAL
{
    public class LeaderboardDAL : ILeaderboardDAL
    {
        private IMongoCollection<Result> _results;
        private IUserDAL _userDAL;

        public LeaderboardDAL(IKengurDatabaseSettings settings, IMongoClient mongoClient, IUserDAL userDAL)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _results = database.GetCollection<Result>(settings.ResultCollectionName);
            _userDAL = userDAL;
        }

        public async Task<bool> AddResult(Result result)
        {
            await _results.InsertOneAsync(result);
            return await _userDAL.UpdateUser(result.User!.Email, result.Points);
        }

        public async Task<List<Result>> GetLeaderboard(string Class)
        {
            var resultsCursor = await _results.FindAsync(result => result.Class == Class);
            var resultsToList= await resultsCursor.ToListAsync();
            return resultsToList.OrderByDescending(r => r.Points).ToList();
        }
    }
}
