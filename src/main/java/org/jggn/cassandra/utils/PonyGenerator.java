package org.jggn.cassandra.utils;
/***
 * Source : https://www.fantasynamegenerators.com/my-little-pony-names.php
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.jggn.cassandra.models.EnumGenre;
import org.jggn.cassandra.models.EnumType;
import org.jggn.cassandra.models.Pony;
import org.springframework.stereotype.Component;
@Component
public class PonyGenerator {
	Random random = new Random();
	String[] standAloneMare = {"Blueberry", "Twinkletoes", "Starfish", "Nettlekiss", "Hazelblossom", "Dazzleflash", "Buttercup", "Bubblegum", "Cherry Blossom", "Snowflake", "Water Lilly", "Twinkle Star", "Saffron", "Pumpkin", "Cinnamon Twirl", "Sweetie Pie", "Cutie Pie", "Celeste", "Rose Petal", "Sugar Song", "Honey Bee", "Scarlet", "Obsidia", "Lollipop", "Sparkle", "Dahlia", "Lucy Light", "Lavender", "Orchid Jewel", "Sapphire", "Sapphire Swing", "Scarlet Harmony", "Sapphire Sunlight", "Lunar Candy", "Midnight Harmony", "Violet Glow", "Velvet Love", "Sapphire Daisy", "Fluffy Candy", "Fluffy Star", "Rainbow Dawn", "Pearl Petunia", "Caramel Candy", "Velvet Kisses", "Ruby Aurora", "Dew Drop", "Solar Kisses", "Ivy Jewel", "Emerald Aura", "Violet Sparkle", "Violet Rain", "Strawberry Fashion", "Ivory Fire", "Shadow Flower", "Delilah Dusk", "Velvet Cupcake", "Star Eyes", "Celestial Song", "Celestial Snowflake", "Snowy Blossom", "Azure Moon", "Azure Shadow", "Mythic Diamond", "Sapphire Flower", "Lunar Flower", "Sapphire Moonlight", "Aqua Lilly", "Lillypad Love", "Lila Love", "Emerald Snow", "Sugar Spice", "Chocolate Harmony", "Electric Harmony", "Ebony Moon", "River Breeze", "Ebony Breeze", "Crystal Rose", "Diamond Blossom", "Ice Blossom", "Phantasia", "Starry Night", "Moon Petal", "Emerald Dream", "Sandy Shadow", "Ivory Charm", "Lucky Star", "Lucky Lucy", "Ocean Breeze", "Cherry Berry", "Caramel Smooch", "Caramel Kisses", "Amethyst Rose", "Lunar Love", "Scarlet Shadow", "Mythic Fashion", "Little Harmony", "Little Snowflake", "Starry Diamond", "Starry Swirl", "Nightlight Nourish", "Honey Cake", "Amber Night", "Amber Gem", "Flawless Gem", "Twilight Snowflake", "Fluffy Wings"};
	String[] namesMare = {"Amber", "Amethyst", "Aqua", "Azure", "Caramel", "Celestial", "Cherry", "Chocolate", "Cinnamon", "Crystal", "Cutie", "Delilah", "Dew", "Diamond", "Ebony", "Electric", "Emerald", "Flawless", "Fluffy", "Honey", "Ice", "Ivory", "Ivy", "Lila", "Lillypad", "Little", "Lucky", "Lucy", "Lunar", "Midnight", "Moon", "Mythic", "Nightlight", "Ocean", "Orchid", "Pearl", "Rainbow", "River", "Rose", "Ruby", "Sandy", "Sapphire", "Scarlet", "Shadow", "Snowy", "Solar", "Star", "Starry", "Strawberry", "Sugar", "Sweetie", "Twilight", "Twinkle", "Velvet", "Violet", "Water"};
	String[] otherMare = {"Aura", "Aurora", "Bee", "Berry", "Blossom", "Breeze", "Cake", "Candy", "Charm", "Cupcake", "Daisy", "Dawn", "Diamond", "Dream", "Drop", "Dusk", "Eyes", "Fashion", "Fire", "Flower", "Gem", "Glow", "Harmony", "Jewel", "Kisses", "Light", "Lilly", "Love", "Moon", "Moonlight", "Night", "Nourish", "Petal", "Petunia", "Pie", "Rain", "Rose", "Shadow", "Smooch", "Snow", "Snowflake", "Song", "Sparkle", "Spice", "Star", "Sunlight", "Swing", "Swirl", "Twirl", "Wings"};

	String[] standAloneColt = {"Blueberry", "Twinkletoes", "Starfish", "Nettlekiss", "Hazelblossom", "Dazzleflash", "Apple Specter", "Applesauce", "Arctic Breeze", "Astral Thunder", "Berry White", "Big B", "Blaze", "Bouncer", "Brisk Bronco", "Bulky Buster", "Caramel Crunch", "Cloud Sweeper", "Cobalt", "Coco-Nut", "Colt Feet", "Colt Ice", "Comet Flash", "Crimson Mask", "Crimson Moon", "Crimson Vision", "Dapper Dash", "Dapper Force", "Dark Meadow", "Dark Snow", "Dark Specter", "Duke Bristle", "Duke Starlight", "Duke Venture", "Emerald Mask", "Fancy Hooves", "Flawless Victory", "Golden Flash", "Ivory Spark", "Jackpot Star", "Jade Jester", "Last Legacy", "Little Snow", "Lost Legacy", "Marble Mark", "Master Facade", "Master Metal", "Maverick", "Midnight Hero", "Mister Bristle", "Mister Dare", "Moonlight Hunter", "Moonshadow Colt", "Moonshadow Sprint", "Moonstone Mustang", "Mythic Haze", "Night Twister", "Nimble Force", "Onyx", "Onyx Armor", "Onyx Bolt", "Onyx Justice", "Onyx Star", "Platinum Night", "Prince Prickle", "Raggedy Randy", "Rapid Shadow", "Rocky Road", "Sapphire Spirit", "Sapphire Twister", "Shadow Mark", "Shining Star", "Silver Mane", "Silver Shine", "Silver Spirit", "Silver Whiskers", "Sky Chaser", "Sky Scraper", "Smoky", "Soaring Surfer", "Solar Comet", "Star Chaser", "Star Whistle", "Steel Mustang", "Steel Stud", "Straight Arrow", "Sundance", "Sunrise Storm", "Sweet Sorbet", "Swift Bolt", "Tango", "Thunder Bolt", "Thunder Charge", "Thunder Colt", "Thunder Spark", "Thunder Tail", "Thunder Wing", "Thunderhoof", "Tiny Thunder", "Twilight Thunder", "Velvet Chaser", "Wild Ace", "Wild Strikes", "Winter Gust", "Yellow Rock"};
	String[] namesColt = {"Apple", "Arctic", "Astral", "Berry", "Brisk", "Bulky", "Caramel", "Cloud", "Colt", "Comet", "Crimson", "Dapper", "Dark", "Duke", "Emerald", "Fancy", "Flawless", "Golden", "Ivory", "Jackpot", "Jade", "Little", "Marble", "Master", "Midnight", "Mister", "Moonlight", "Moonshadow", "Moonstone", "Mythic", "Night", "Nimble", "Onyx", "Platinum", "Prince", "Rapid", "Rocky", "Sapphire", "Shadow", "Shining", "Silver", "Sky", "Solar", "Star", "Steel", "Straight", "Sunrise", "Sweet", "Swift", "Thunder", "Tiny", "Twilight", "Velvet", "Wild", "Winter", "Yellow"};
	String[] otherColt = {"Ace", "Armor", "Arrow", "Bolt", "Breeze", "Bristle", "Bronco", "Buster", "Charge", "Chaser", "Colt", "Comet", "Crunch", "Dare", "Dash", "Facade", "Feet", "Flash", "Force", "Gust", "Haze", "Hero", "Hooves", "Hunter", "Ice", "Jester", "Justice", "Mane", "Mark", "Mask", "Meadow", "Metal", "Moon", "Mustang", "Night", "Prickle", "Road", "Rock", "Shadow", "Shine", "Snow", "Sorbet", "Spark", "Specter", "Spirit", "Sprint", "Star", "Starlight", "Storm", "Strikes", "Sweeper", "Tail", "Thunder", "Twister", "Venture", "Victory", "Vision", "Whiskers", "Whistle", "White", "Wing"};

	String[] Location = {"Appleloosa","Canterlot","Cloudsdale","Crystal Empire","Fillydelphia","Griffonstone","Las Pegasus","Manehattan","Ponyville"};

	public List<Pony> generatePonies(int size)
	{
		List<Pony> ponies =new ArrayList<>();
		for(int i=0;i<size;i++)ponies.add(generatePony());
		return ponies;
	}

	public Pony generatePony()
	{
		//1- On génère un Genre (Mare 0.75,Colt 0.25 -> On est dans une serie pour filles ! )
		float f =random.nextFloat();
		EnumGenre genre =f>0.75f?EnumGenre.COLT:EnumGenre.MARE;		
		//Standalone ou couple Name/Other (30/70)
		String name = genre ==EnumGenre.MARE?generateMareName():generateColtName();
		//Ville
		String town = Location[random.nextInt(Location.length)];
		EnumType ponyType = EnumType.values()[random.nextInt(EnumType.values().length)];
		Pony p = new Pony();
		p.setAge(random.nextInt(35));
		p.setBirthPlace(town);
		p.setGenre(genre);
		p.setName(name);
		p.setType(ponyType);
		p.setPonyId(UUID.randomUUID());
		return p;
		
	}
	
	private String generateColtName()
	{
		Float f =random.nextFloat();
		return f>0.70?standAloneColt[random.nextInt(standAloneColt.length)]:namesColt[random.nextInt(namesColt.length)]+" "+otherColt[random.nextInt(otherColt.length)];
	}
	
	private String generateMareName()
	{
		Float f =random.nextFloat();
		return f>0.70?standAloneMare[random.nextInt(standAloneMare.length)]:namesMare[random.nextInt(namesMare.length)]+" "+otherMare[random.nextInt(otherMare.length)];

	}
}
