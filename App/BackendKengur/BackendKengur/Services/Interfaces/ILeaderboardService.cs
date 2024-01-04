using BackendKengur.DTOComponents;
using BackendKengur.Models;

namespace BackendKengur.Services.Interfaces
{
    public interface ILeaderboardService
    {
        Task<bool> AddResult(ResultDTO result);

        Task<List<Result>> GetLeaderboard(string Class);
    }
}
