package com.example.bikestore.data

import com.example.bikestore.R


fun getBikesList():List<Bike>{
    val bikesList = mutableListOf<Bike>()

    bikesList.add(Bike("Tailfin Alloy Rack", R.drawable.bike_1,"Alloy Rack with pannier mounts- Perfect for riders who need a luggage carrying system that can adapt to any use.",4.0f,35,
           BikeFeatures(false,true,true),false,""))

    bikesList.add(Bike("Vintage Bike Black", R.drawable.bike_2,"Beautiful, reliable and elegant; the Ladies Vintage Classic Plus is easy to fall in love with. ",3f,15,
        BikeFeatures(true,false,true),false,""))

    bikesList.add(Bike("Road Bike, (Matt Black, Medium)", R.drawable.bike_3,"Best bike for cities touring, commuting to work spaces and can be used for cycling",3.8f,50,
        BikeFeatures(false,false,true),false,""))

    bikesList.add(Bike("AMN800 Gear Bicycle", R.drawable.bike_4,"Amardeep cycles AMN800 Gear Bicycle - Mountain Sports Bike for Adults - MTB Gear Dual Disc Break - 85% Asseble (Grey Black) 17 Inches",4.3f,25,
        BikeFeatures(false,false,true),false,""))

    bikesList.add(Bike("Wayyo Gree'oo Go-Getter", R.drawable.bike_5,"Wayyo Gree'oo Go-Getter Steel Thin Tyres, Single Speed Fixie without Gear Bicycle (Green,Wheel Size - 27 inches, Frame Size - 18 inches)",3.5f,30,
        BikeFeatures(false,true,true),false,""))

    return bikesList
}