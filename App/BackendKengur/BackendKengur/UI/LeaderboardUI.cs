using BackendKengur.DTOComponents;
using BackendKengur.Models;
using BackendKengur.Services.Interfaces;
using BackendKengur.UI.Interfaces;

namespace BackendKengur.UI
{
    public class LeaderboardUI : ILeaderboardUI
    {

        private readonly ILeaderboardService leaderboardService;

        public LeaderboardUI(ILeaderboardService leaderboardService)
        {
            this.leaderboardService = leaderboardService;
        }

        public MyResponse AddResult(ResultDTO result)
        {
            var succeed = leaderboardService.AddResult(result);
            if (!succeed)
                return new MyResponse() { StatusCode = StatusCodes.Status404NotFound, Message="Rezultat upisan u rang-listu." };

            return new MyResponse() { StatusCode = StatusCodes.Status200OK, Message = "Rezultat upisan u rang-listu." };
        }

        public List<Result> GetLeaderboard(string Class)
        {
            return leaderboardService.GetLeaderboard(Class);
        }
    }
}
