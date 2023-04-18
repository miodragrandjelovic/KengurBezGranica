using BackendKengur.DAL.Interfaces;
using BackendKengur.DTOComponents;
using BackendKengur.Models;
using BackendKengur.Services.Interfaces;

namespace BackendKengur.Services
{
    public class LeaderboardService : ILeaderboardService
    {
        private readonly ILeaderboardDAL leaderboardDAL;
        private readonly IUserDAL userDAL;

        public LeaderboardService(ILeaderboardDAL leaderboardDAL, IUserDAL userDAL)
        {
            this.leaderboardDAL = leaderboardDAL;
            this.userDAL = userDAL;
        }

        public bool AddResult(ResultDTO result)
        {
            var user = userDAL.GetUser(result.email);

            var modelResult = new Result() { 
                Class = result.Class,
                User = new UserLeaderboardDTO()
                {
                    Email = user!.Email,
                    FirstName = user.FirstName,
                    LastName = user.LastName, 
                } ,
                Points = result.Points };

            return leaderboardDAL.AddResult(modelResult);
        }

        public List<Result> GetLeaderboard(string Class)
        {
            return leaderboardDAL.GetLeaderboard(Class);
        }
    }
}
