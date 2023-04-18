using BackendKengur.DTOComponents;
using BackendKengur.Models;

namespace BackendKengur.Services.Interfaces
{
    public interface ILeaderboardService
    {
        bool AddResult(ResultDTO result);

        List<Result> GetLeaderboard(string Class);
    }
}
