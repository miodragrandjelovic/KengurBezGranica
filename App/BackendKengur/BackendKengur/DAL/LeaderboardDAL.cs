using BackendKengur.DAL.Interfaces;
using BackendKengur.Models;
using BackendKengur.Models.Interfaces;
using MongoDB.Driver;

namespace BackendKengur.DAL
{
    public class LeaderboardDAL : ILeaderboardDAL
    {
        private IMongoCollection<Result> _results;

        public LeaderboardDAL(IKengurDatabaseSettings settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _results = database.GetCollection<Result>(settings.ResultCollectionName);
        }

        public bool AddResult(Result result)
        {
            _results.InsertOne(result);
            return true;
        }

        public List<Result> GetLeaderboard(string Class)
        {
            return _results.Find(result=> result.Class == Class).SortByDescending(r => r.Points).ToList();
        }
    }
}
