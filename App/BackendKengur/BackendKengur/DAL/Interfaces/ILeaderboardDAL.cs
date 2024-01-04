using BackendKengur.Models;

namespace BackendKengur.DAL.Interfaces
{
    public interface ILeaderboardDAL
    {
        Task<bool> AddResult(Result result);
        Task<List<Result>> GetLeaderboard(string Class);
    }
}
