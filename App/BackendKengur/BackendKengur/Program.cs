using BackendKengur.DAL;
using BackendKengur.DAL.Interfaces;
using BackendKengur.Models;
using BackendKengur.Models.Interfaces;
using BackendKengur.UI.Interfaces;
using BackendKengur.UI;
using Microsoft.Extensions.Options;
using MongoDB.Driver;
using BackendKengur.Services.Interfaces;
using BackendKengur.Services;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.Configure<KengurDatabaseSettings>(
    builder.Configuration.GetSection(nameof(KengurDatabaseSettings)));

builder.Services.AddSingleton<IKengurDatabaseSettings>(s =>
    s.GetRequiredService<IOptions<KengurDatabaseSettings>>().Value);


builder.Services.AddSingleton<IMongoClient>(s =>
    new MongoClient(builder.Configuration.GetValue<string>("KengurDatabaseSettings:ConnectionString")));


/*// CORS Configuration 
builder.Services.AddCors(options =>
    options.AddDefaultPolicy(builder =>
    {
        builder.WithOrigins("https://localhost:7231")
        .AllowAnyHeader()
        .AllowAnyMethod();
    }
));*/

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();


#region 'DAL Dependencies'

builder.Services.AddScoped<ISchoolDAL, SchoolDAL>();


#endregion

#region 'Services - BL Dependencies'

builder.Services.AddTransient<ISchoolService, SchoolService>();


#endregion

#region 'UI - Dependencies'

builder.Services.AddTransient<ISchoolUI, SchoolUI>();


#endregion

builder.Services.AddTransient<IHttpContextAccessor, HttpContextAccessor>();


var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

/*app.UseCors();*/

app.MapControllers();

app.Run();
