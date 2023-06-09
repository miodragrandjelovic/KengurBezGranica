﻿using BackendKengur.Models;

namespace BackendKengur.DAL.Interfaces
{
    public interface IUserDAL
    {
        bool AddNewUser(User user);
        User? GetUser(string email);
    }
}
