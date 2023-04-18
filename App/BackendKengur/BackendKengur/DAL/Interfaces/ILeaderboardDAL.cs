using BackendKengur.Models;

namespace BackendKengur.DAL.Interfaces
{
    public interface ILeaderboardDAL
    {
        bool AddResult(Result result);
        List<Result> GetLeaderboard(string Class);
    }
}
